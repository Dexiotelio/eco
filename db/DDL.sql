-- Tabla de clientes
CREATE TABLE client (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    age INT CHECK (age > 18),
    gender TEXT CHECK (gender IN ('male', 'female', 'other')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabla de direcciones
CREATE TABLE address (
    id_address BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT REFERENCES client (id) ON DELETE CASCADE,
    street TEXT NOT NULL,
    street_number TEXT NOT NULL,
    apartment_number TEXT,
    neighborhood TEXT NOT NULL,
    city TEXT NOT NULL,
    state TEXT NOT NULL,
    postal_code TEXT NOT NULL,
    country TEXT NOT NULL,
    phone TEXT NOT NULL,
    address_type TEXT NOT NULL
);

-- Índice en la tabla address
CREATE INDEX idx_address_client_id ON address USING BTREE (client_id);

-- Tipos de estado del carrito
CREATE TYPE cart_status AS ENUM ('ACTIVE', 'ABANDONED', 'COMPLETED', 'CANCELLED');

-- Tabla de carritos
CREATE TABLE cart (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES client (id),
    status cart_status NOT NULL,
    total_amount NUMERIC(10, 2) NOT NULL,
    coupon_code TEXT,
    is_checked_out BOOLEAN NOT NULL,
    shipping_address_id BIGINT REFERENCES address (id_address),
    payment_method TEXT,
    expires_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabla de artículos del carrito
CREATE TABLE cart_item (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cart_id BIGINT NOT NULL REFERENCES cart (id),
    product_id BIGINT NOT NULL,
    product_name TEXT NOT NULL,
    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    product_image TEXT
);

-- Índice en la tabla cart_item
CREATE INDEX idx_cart_item_cart_id ON cart_item USING BTREE (cart_id);

-- Tipos de estado del inventario
CREATE TYPE inventory_status AS ENUM ('IN_STOCK', 'OUT_OF_STOCK', 'BACKORDERED');

-- Tabla de descuentos
CREATE TABLE discount (
    discount_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    min_quantity REAL CHECK (min_quantity >= 0),
    discount_percentage REAL CHECK (discount_percentage > 0 AND discount_percentage <= 100),
    active BOOLEAN NOT NULL,
    expiry_date TIMESTAMP,
    version BIGINT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabla de inventario
CREATE TABLE inventory (
    inventory_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    quantity INT CHECK (quantity >= 0) NOT NULL,
    cost NUMERIC(10, 2) CHECK (cost > 0),
    status inventory_status,
    version BIGINT,
    supplier TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tipos de productos
CREATE TYPE product_type AS ENUM ('ELECTRONICS', 'ACCESSORIES', 'PERIPHERALS', 'NETWORKING');
CREATE TYPE product_category AS ENUM ('PC', 'NOTEBOOK', 'PHONE', 'TABLET', 'MONITOR', 'ACCESSORY', 'PERIPHERAL', 'NETWORKING');

-- Tabla de productos
CREATE TABLE product (
    product_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL,
    price NUMERIC(10, 2) CHECK (price >= 0.0) NOT NULL,
    description TEXT NOT NULL,
    image TEXT,
    type product_type NOT NULL,
    category_id BIGINT REFERENCES category(category_id),
    inventory_id BIGINT REFERENCES inventory (inventory_id),
    discount_id BIGINT REFERENCES discount (discount_id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabla de especificaciones de productos
CREATE TABLE specification (
    product_id BIGINT PRIMARY KEY REFERENCES product (product_id),
    os TEXT,
    storage TEXT,
    keyboard TEXT,
    touchpad TEXT,
    camera TEXT,
    battery TEXT,
    weight REAL
);

-- Especificaciones adicionales para productos
CREATE TABLE specification_processors (
    product_id BIGINT REFERENCES product (product_id),
    processor TEXT
);

CREATE TABLE specification_graphics (
    product_id BIGINT REFERENCES product (product_id),
    graphics TEXT
);

CREATE TABLE specification_screens (
    product_id BIGINT REFERENCES product (product_id),
    screen TEXT
);

CREATE TABLE specification_memory (
    product_id BIGINT REFERENCES product (product_id),
    memory TEXT
);

CREATE TABLE specification_ports (
    product_id BIGINT REFERENCES product (product_id),
    port TEXT
);

CREATE TABLE specification_audio (
    product_id BIGINT REFERENCES product (product_id),
    audio TEXT
);

CREATE TABLE specification_wireless_connection (
    product_id BIGINT REFERENCES product (product_id),
    wireless_connection TEXT
);

-- Tipos de estado del pedido
CREATE TYPE order_status AS ENUM ('PENDING', 'PROCESSED', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'COMPLETED');

-- Tipos de método de pago
CREATE TYPE payment_method AS ENUM ('CREDIT_CARD', 'DEBIT_CARD', 'PAYPAL', 'BANK_TRANSFER');

-- Tabla de pagos
CREATE TABLE payment (
    payment_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id BIGINT NOT NULL REFERENCES "order" (id),
    status payment_status NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    payment_method TEXT,
    transaction_id TEXT,
    payment_date TIMESTAMP,
    payment_details TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Tabla de pedidos
CREATE TABLE "order" (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES client (id),
    order_date TIMESTAMP NOT NULL,
    status order_status NOT NULL,
    total_amount NUMERIC(10, 2) NOT NULL,
    payment_id BIGINT REFERENCES payment (payment_id),
    payment_method payment_method,
    shipping_address_id BIGINT REFERENCES address (id_address),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabla de artículos del pedido
CREATE TABLE order_item (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id BIGINT NOT NULL REFERENCES "order" (id),
    product_id BIGINT NOT NULL REFERENCES product (product_id),
    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

-- Índice en la tabla order_item
CREATE INDEX idx_order_item_order_id ON order_item USING BTREE (order_id);

-- Tipos de estado del voucher
CREATE TYPE voucher_status AS ENUM ('ACTIVE', 'REDEEMED', 'EXPIRED', 'INACTIVE');

-- Tabla de vouchers
CREATE TABLE voucher (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id BIGINT NOT NULL REFERENCES "order" (id),
    client_id BIGINT NOT NULL REFERENCES client (id),
    amount NUMERIC(10, 2) NOT NULL,
    status voucher_status NOT NULL,
    voucher_code TEXT,
    purchase_date TIMESTAMP NOT NULL
);

-- Tabla de artículos del voucher
CREATE TABLE voucher_item (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    voucher_id BIGINT NOT NULL REFERENCES voucher (id),
    product_id BIGINT NOT NULL REFERENCES product (product_id),
    quantity INT CHECK (quantity >= 1) NOT NULL,
    price NUMERIC(10, 2) CHECK (price > 0) NOT NULL
);

-- Índice en la tabla voucher_item
CREATE INDEX idx_voucher_item_voucher_id ON voucher_item USING BTREE (voucher_id);

-- Tabla de categorías
CREATE TABLE category (
    category_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL UNIQUE,
    description TEXT,
    image_url TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    parent_category_id BIGINT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    FOREIGN KEY (parent_category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

-- Tabla de reseñas
CREATE TABLE review (
    review_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT NOT NULL REFERENCES product (product_id),
    client_id BIGINT NOT NULL REFERENCES client (id),
    rating INT CHECK (rating BETWEEN 1 AND 5) NOT NULL,
    comment TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Tabla de historial de precios
CREATE TABLE price_history (
    history_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT NOT NULL REFERENCES product (product_id),
    old_price NUMERIC NOT NULL,
    new_price NUMERIC NOT NULL,
    changed_at TIMESTAMP DEFAULT NOW()
);

-- Tabla de cupones
CREATE TABLE coupon (
    coupon_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code TEXT UNIQUE NOT NULL,
    discount_percentage REAL CHECK (discount_percentage > 0 AND discount_percentage <= 100) NOT NULL,
    expiry_date TIMESTAMP,
    active BOOLEAN NOT NULL
);

-- Tabla de listas de deseos
CREATE TABLE wishlist (
    wishlist_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES client (id),
    product_id BIGINT NOT NULL REFERENCES product (product_id),
    added_at TIMESTAMP DEFAULT NOW()
);

-- Tabla de historial de pedidos
CREATE TABLE order_history (
    history_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id BIGINT NOT NULL REFERENCES "order" (id),
    status order_status NOT NULL,
    changed_at TIMESTAMP DEFAULT NOW()
);

-- Tabla de tickets de soporte
CREATE TABLE support_ticket (
    ticket_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES client (id),
    subject TEXT NOT NULL,
    description TEXT NOT NULL,
    status TEXT CHECK (status IN ('OPEN', 'IN_PROGRESS', 'CLOSED')) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
