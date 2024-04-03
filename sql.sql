CREATE DATABASE Polypro
GO
USE Polypro
GO
CREATE TABLE NhanVien(
MaNV nvarchar(50) NOT NULL,
MatKhau nvarchar(50) NOT NULL,
HoTen nvarchar(50) NOT NULL,
VaiTro bit NOT NULL DEFAULT 0,
PRIMARY KEY(MaNV)
)
GO
CREATE TABLE ChuyenDe(
MaCD nchar(5) NOT NULL,
TenCD nvarchar(50) NOT NULL,
HocPhi float NOT NULL DEFAULT 0,
ThoiLuong int NOT NULL DEFAULT 30,
Hinh nvarchar(50) NOT NULL ,
MoTa nvarchar(255) NOT NULL,
PRIMARY KEY(MaCD),
UNIQUE(TenCD),
CHECK(HocPhi >= 0 AND ThoiLuong > 0)
)
GO
CREATE TABLE NguoiHoc(
MaNH nchar(7) NOT NULL,
HoTen nvarchar(50) NOT NULL,
NgaySinh date NOT NULL,
GioiTinh bit NOT NULL DEFAULT 0,
DienThoai nvarchar(50) NOT NULL,
Email nvarchar(50) NOT NULL,
GhiChu nvarchar(max) NULL,
MaNV nvarchar(50) NOT NULL,
NgayDK date NOT NULL DEFAULT getdate(),
PRIMARY KEY(MaNH)
)
GO
CREATE TABLE KhoaHoc(
MaKH int IDENTITY(1,1) NOT NULL,
MaCD nchar(5) NOT NULL,
HocPhi float NOT NULL DEFAULT 0,
ThoiLuong int NOT NULL DEFAULT 0,
NgayKG date NOT NULL,
GhiChu nvarchar(50) NULL,
MaNV nvarchar(50) NOT NULL,
NgayTao date NOT NULL DEFAULT getdate(),
PRIMARY KEY(MaKH),
CHECK(HocPhi >= 0 AND ThoiLuong > 0),
FOREIGN KEY (MaCD) REFERENCES ChuyenDe(MaCD) ON UPDATE CASCADE,
FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)
GO
CREATE TABLE HocVien(
MaHV int IDENTITY(1,1) NOT NULL,
MaKH int NOT NULL,
MaNH nchar(7) NOT NULL,
Diem float NOT NULL,
PRIMARY KEY(MaHV),
UNIQUE(MaKH, MaNH),
FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE,
FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON UPDATE CASCADE
)

-- Insert into NhanVien table
INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro)
VALUES (N'NV001', N'matkhau123', N'Nguyễn Văn A', 1),
       (N'NV002', N'mk456', N'Phạm Thị B', 0),
       (N'NV003', N'password789', N'Trần Văn C', 0);
	   select * from NhanVien;
-- Insert into ChuyenDe table
INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa)
VALUES (N'C001', N'Lập trình C# căn bản', 2000000, 45, N'csharp.jpg', N'Chương trình học lập trình C# từ cơ bản đến nâng cao.'),
       (N'C002', N'Tiếng Anh giao tiếp', 1500000, 60, N'tienganh.jpg', N'Học tiếng Anh giao tiếp hàng ngày.'),
       (N'C003', N'Thiết kế đồ họa Photoshop', 2500000, 30, N'photoshop.jpg', N'Học Photoshop từ A đến Z.');

-- Insert into NguoiHoc table
INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK)
VALUES (N'NH001', N'Nguyễn Thị H', '1990-05-20', 0, N'0123456789', N'nguyenthih@example.com', NULL, N'NV001', '2023-01-05'),
       (N'NH002', N'Lê Văn D', '1985-10-15', 1, N'0987654321', N'levand@example.com', N'Ghi chú cho học viên', N'NV002', '2023-02-10'),
       (N'NH003', N'Phạm Văn E', '1998-03-08', 1, N'0369876543', N'phamvane@example.com', NULL, N'NV002', '2023-03-20');

-- Insert into KhoaHoc table
INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao)
VALUES (N'C001', 2000000, 45, '2023-02-01', N'Ghi chú cho khóa học 1', N'NV001', '2023-01-20'),
       (N'C002', 1500000, 60, '2023-03-15', NULL, N'NV002', '2023-02-05'),
       (N'C003', 2500000, 30, '2023-04-10', NULL, N'NV003', '2023-03-10');

-- Insert into HocVien table
INSERT INTO HocVien (MaKH, MaNH, Diem)
VALUES (1, N'NH001', 8.5),
       (2, N'NH002', 7.0),
       (3, N'NH003', 9.3);


-- cái dưới này tạo riêng
CREATE PROC sp_ThongKeNguoiHoc
AS BEGIN
SELECT
YEAR(NgayDK) Nam,
COUNT(*) SoLuong,
MIN(NgayDK) DauTien,
MAX(NgayDK) CuoiCung
FROM NguoiHoc
GROUP BY YEAR(NgayDK)
END

--

CREATE PROC sp_ThongKeDoanhThu(@Year INT)
AS BEGIN
SELECT
TenCD ChuyenDe,
COUNT(DISTINCT kh.MaKH) SoKH,
COUNT(hv.MaHV) SoHV,
SUM(kh.HocPhi) DoanhThu,
MIN(kh.HocPhi) ThapNhat,
MAX(kh.HocPhi) CaoNhat,
AVG(kh.HocPhi) TrungBinh
FROM KhoaHoc kh
JOIN HocVien hv ON kh.MaKH=hv.MaKH
JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
WHERE YEAR(NgayKG) = @Year
GROUP BY TenCD
END
---------------------------------

CREATE PROC sp_ThongKeDiem
AS BEGIN
SELECT
TenCD ChuyenDe,
COUNT(MaHV) SoHV,
MIN(Diem) ThapNhat,
MAX(Diem) CaoNhat,
AVG(Diem) TrungBinh
FROM KhoaHoc kh
JOIN HocVien hv ON kh.MaKH=hv.MaKH
JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
GROUP BY TenCD
END

----------------------------
CREATE PROC sp_BangDiem(@MaKH INT)
AS BEGIN
SELECT
nh.MaNH,
nh.HoTen,
hv.Diem
FROM HocVien hv
JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
WHERE hv.MaKH = @MaKH
ORDER BY hv.Diem DESC
END
