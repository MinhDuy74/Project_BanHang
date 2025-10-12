create database shop_banhang;
CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(100) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    isSell TINYINT(1) DEFAULT 0,    -- 1: là người bán, 0: khách thường
    isAdmin TINYINT(1) DEFAULT 0    -- 1: là admin, 0: không phải admin
);

-- Bảng danh mục sản phẩm (Category)
CREATE TABLE Category (
    cid INT AUTO_INCREMENT PRIMARY KEY,
    cname VARCHAR(100) NOT NULL
);

-- Bảng sản phẩm (Product)
CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255), -- Ảnh đại diện mặc định
    price DOUBLE,
    title VARCHAR(255),
    description TEXT,
    cateID INT,
    sell_ID INT,
    FOREIGN KEY (cateID) REFERENCES Category(cid),
    FOREIGN KEY (sell_ID) REFERENCES Account(id)
);

-- Bảng màu sắc sản phẩm (Product Colors)
CREATE TABLE product_colors (
    color_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    color_name VARCHAR(50) NOT NULL,
    color_code VARCHAR(10), -- ví dụ #ffffff
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Bảng ảnh sản phẩm theo màu (Product Images)
CREATE TABLE product_images (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    color_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    image_order INT DEFAULT 0,
    FOREIGN KEY (product_id) REFERENCES Product(id),
    FOREIGN KEY (color_id) REFERENCES product_colors(color_id)
);

-- (Nếu có chức năng đặt hàng) Bảng đơn hàng
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES Account(id)
);

-- (Nếu có chức năng đặt hàng) Bảng chi tiết đơn hàng
CREATE TABLE OrderDetail (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    color_id INT, -- màu cụ thể được mua
    quantity INT,
    price DOUBLE,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(id),
    FOREIGN KEY (color_id) REFERENCES product_colors(color_id)
);
-- Thêm tài khoản mẫu
INSERT INTO Account (user, pass, isSell, isAdmin) VALUES
('admin', 'admin123', 0, 1),
('seller1', 'sellerpass', 1, 0),
('user1', 'userpass', 0, 0);

-- Thêm danh mục sản phẩm
INSERT INTO Category (cname) VALUES
('Giày thể thao'),
('Giày tây'),
('Dép');

-- Thêm sản phẩm
INSERT INTO Product (name, image, price, title, description, cateID, sell_ID) VALUES
('Giày Nike Air Max', 'nike_airmax.jpg', 2500000, 'Nike Air Max Chính Hãng', 'Giày chạy bộ, form thể thao, chất liệu nhẹ', 1, 2),
('Giày Tây Nam Classic', 'tay_classic.jpg', 1200000, 'Giày tây da cao cấp', 'Chất liệu da thật, phong cách lịch lãm', 2, 2),
('Dép Tông Mùa Hè', 'dep_tong.jpg', 150000, 'Dép tông bền đẹp', 'Dễ chịu, đi biển cực thích', 3, 2);

-- Thêm màu sắc cho sản phẩm
INSERT INTO product_colors (product_id, color_name, color_code) VALUES
(1, 'Trắng', '#FFFFFF'),
(1, 'Đen', '#000000'),
(2, 'Nâu', '#8B4513'),
(2, 'Đen', '#000000'),
(3, 'Xanh Dương', '#0000FF');

-- Thêm ảnh cho từng màu sản phẩm
INSERT INTO product_images (product_id, color_id, image_url, image_order) VALUES
(1, 1, 'nike_airmax_white_1.jpg', 1),
(1, 1, 'nike_airmax_white_2.jpg', 2),
(1, 2, 'nike_airmax_black_1.jpg', 1),
(1, 2, 'nike_airmax_black_2.jpg', 2),
(2, 3, 'tay_classic_brown_1.jpg', 1),
(2, 4, 'tay_classic_black_1.jpg', 1),
(3, 5, 'dep_tong_blue_1.jpg', 1);

-- Thêm đơn hàng mẫu
INSERT INTO Orders (user_id, order_date, status) VALUES
(3, '2025-10-01 09:00:00', 'pending');

-- Thêm chi tiết đơn hàng cho đơn hàng trên (mua đôi Nike màu trắng và dép xanh dương)
INSERT INTO OrderDetail (order_id, product_id, color_id, quantity, price) VALUES
(1, 1, 1, 1, 2500000),
(1, 3, 5, 2, 150000);

