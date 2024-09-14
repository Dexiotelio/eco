create table client (
  id bigint primary key generated always as identity,
  firstname text not null,
  lastname text not null,
  username text not null unique,
  email text not null unique,
  password text not null,
  age int check (age > 18),
  gender text check (gender in ('male', 'female', 'other')),
  createdat timestamp with time zone default now(),
  updatedat timestamp with time zone default now()
);

create table address (
  idaddress bigint primary key generated always as identity,
  client_id bigint references client (id) on delete cascade,
  street text not null,
  streetnumber text not null,
  apartmentnumber text,
  neighborhood text not null,
  city text not null,
  state text not null,
  postalcode text not null,
  country text not null,
  phone text not null,
  addresstype text not null
);

create index idx_address_client_id on address using btree (client_id);

-- tipos de estado del carrito
create type cart_status as enum('ACTIVE', 'ABANDONED', 'COMPLETED', 'CANCELLED');

-- Crear carrito
create table cart (
  id bigint primary key generated always as identity,
  client_id bigint not null references client (id),
  status cart_status not null,
  totalamount numeric(10, 2) not null,
  couponcode text,
  ischeckedout boolean not null,
  shipping_address_id bigint references address (idaddress),
  paymentmethod text,
  expiresat timestamp with time zone,
  createdat timestamp with time zone default now(),
  updatedat timestamp with time zone default now()
);

create table cart_item (
  id bigint primary key generated always as identity,
  cart_id bigint not null references cart (id),
  product_id bigint not null,
  productname text not null,
  quantity int not null,
  price numeric(10, 2) not null,
  totalprice numeric(10, 2) not null,
  productimage text
);

create index idx_cart_item_cart_id on cart_item using btree (cart_id);

-- tipos de estado del inventario
create type inventory_status as enum('IN_STOCK', 'OUT_OF_STOCK', 'BACKORDERED');

-- Crear la tabla de descuentos
create table discount (
  discount_id bigint primary key generated always as identity,
  min_quantity real check (min_quantity >= 0),
  discount_percentage real check (
    discount_percentage > 0
    and discount_percentage <= 100
  ),
  active boolean not null,
  expiry_date timestamp,
  version bigint,
  created_at timestamp with time zone default now(),
  updated_at timestamp with time zone default now()
);

create table inventory (
  inventory_id bigint primary key generated always as identity,
  quantity int check (quantity >= 0) not null,
  cost numeric(10, 2) check (cost > 0),
  status inventory_status,
  version bigint,
  supplier text,
  created_at timestamp with time zone default now(),
  updated_at timestamp with time zone default now()
);

-- tipos de productos
create type product_type as enum('ELECTRONICS', 'ACCESSORIES', 'PERIPHERALS', 'NETWORKING');
create type product_category as enum('PC',
  'NOTEBOOK',
  'PHONE',
  'TABLET',
  'MONITOR',
  'ACCESSORY',
  'PERIPHERAL',
  'NETWORKING');

create table product (
  product_id bigint primary key generated always as identity,
  name text not null,
  price numeric(10, 2) check (price >= 0.0) not null,
  description text not null,
  image text,
  type product_type not null,
  category product_category not null,
  inventory_id bigint references inventory (inventory_id),
  discount_id bigint references discount (discount_id),
  created_at timestamp with time zone default now(),
  updated_at timestamp with time zone default now()
);

create table specification (
  product_id bigint primary key references product (product_id),
  os text,
  storage text,
  keyboard text,
  touchpad text,
  camera text,
  battery text,
  weight real
);

create table specification_processors (
  product_id bigint references product (product_id),
  processor text
);

create table specification_graphics (
  product_id bigint references product (product_id),
  graphics text
);

create table specification_screens (
  product_id bigint references product (product_id),
  screen text
);

create table specification_memory (
  product_id bigint references product (product_id),
  memory text
);

create table specification_ports (
  product_id bigint references product (product_id),
  port text
);

create table specification_audio (
  product_id bigint references product (product_id),
  audio text
);

create table specification_wireless_connection (
  product_id bigint references product (product_id),
  wireless_connection text
);

-- estado del pedido
create type order_status as enum(
  'PENDING',
  'PROCESSED',
  'SHIPPED',
  'DELIVERED',
  'CANCELLED',
  'COMPLETED'
);

-- método de pago
create type payment_method as enum(
  'CREDIT_CARD',
  'DEBIT_CARD',
  'PAYPAL',
  'BANK_TRANSFER'
);


create table payment (
  payment_id bigint primary key generated always as identity,
  order_id bigint not null references "order" (id),
  status payment_status not null,
  amount numeric(10, 2) not null,
  paymentmethod text,
  transactionid text,
  paymentdate timestamp,
  paymentdetails text,
  createdat timestamp default now()
);

create table "order" (
  id bigint primary key generated always as identity,
  client_id bigint not null references client (id),
  orderdate timestamp not null,
  status order_status not null,
  totalamount numeric(10, 2) not null,
  payment_id bigint references payment (payment_id),
  payment_method payment_method,
  shipping_address_id bigint references address (idaddress),
  created_at timestamp with time zone default now(),
  updated_at timestamp with time zone default now()
);


create table order_item (
  id bigint primary key generated always as identity,
  order_id bigint not null references "order" (id),
  product_id bigint not null references product (product_id),
  quantity int not null,
  price numeric(10, 2) not null
);

create index idx_order_item_order_id on order_item using btree (order_id);

-- estado del voucher
create type voucher_status as enum('ACTIVE', 'REDEEMED', 'EXPIRED', 'INACTIVE');

create table voucher (
  id bigint primary key generated always as identity,
  order_id bigint not null references "order" (id),
  client_id bigint not null references client (id),
  amount numeric(10, 2) not null,
  status voucher_status not null,
  vouchercode text,
  purchasedate timestamp not null
);

create table voucher_item (
  id bigint primary key generated always as identity,
  voucher_id bigint not null references voucher (id),
  product_id bigint not null references product (product_id),
  quantity int check (quantity >= 1) not null,
  price numeric(10, 2) check (price > 0) not null
);

create index idx_voucher_item_voucher_id on voucher_item using btree (voucher_id);

create table category (
  category_id bigint primary key generated always as identity,
  name text unique not null,
  description text
);

create table review (
  review_id bigint primary key generated always as identity,
  product_id bigint not null references product (product_id),
  client_id bigint not null references client (id),
  rating int check (rating between 1 and 5) not null,
  comment text,
  created_at timestamptz default now()
);

create table price_history (
  history_id bigint primary key generated always as identity,
  product_id bigint not null references product (product_id),
  old_price numeric not null,
  new_price numeric not null,
  changed_at timestamptz default now()
);

create table coupon (
  coupon_id bigint primary key generated always as identity,
  code text unique not null,
  discount_percentage real check (
    discount_percentage > 0
    and discount_percentage <= 100
  ) not null,
  expiry_date timestamp,
  active boolean not null
);

create table wishlist (
  wishlist_id bigint primary key generated always as identity,
  client_id bigint not null references client (id),
  product_id bigint not null references product (product_id),
  added_at timestamptz default now()
);

create table order_history (
  history_id bigint primary key generated always as identity,
  order_id bigint not null references "order" (id),
  status order_status not null,
  changed_at timestamptz default now()
);

create table support_ticket (
  ticket_id bigint primary key generated always as identity,
  client_id bigint not null references client (id),
  subject text not null,
  description text not null,
  status text check (status in ('OPEN', 'IN_PROGRESS', 'CLOSED')) not null,
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);
