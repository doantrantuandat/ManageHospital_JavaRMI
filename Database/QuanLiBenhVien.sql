use master
create database HeThongQuanLiBenhVien
on primary
(
	name= QLBV_Data,
	filename='D:\2018 - 2019\PhatTrienUngDung\BaoCaoCuoiCung_DoanTranTuanDat_NgoTuanKiet\PhatTrienUngDung_DoanTranTuanDat_16035741_NgoTuanKiet_16044771\Database\QLBV.mdf',
	size=10mb,
	maxsize=40mb,
	filegrowth=1mb
)
log on
(
	name=QLBH_Log,
	filename='D:\2018 - 2019\PhatTrienUngDung\BaoCaoCuoiCung_DoanTranTuanDat_NgoTuanKiet\PhatTrienUngDung_DoanTranTuanDat_16035741_NgoTuanKiet_16044771\Database\QLBV.ldf',
	size=6mb,
	maxsize=8mb,
	filegrowth=1mb
)

go
use HeThongQuanLiBenhVien
go




  

/* insert into BenhNhan(IDBenhNhan, DiaChi, Ho, SDT, TenBN)
  values ('bn1','Phan Van Tri','Doan','01634054618','Dat')

  insert into BenhNhan(IDBenhNhan, DiaChi, Ho, SDT, TenBN)
  values ('bn2','Le Loi','Vo','0898939106','Tan Luc')*/

  insert into NhanVien(IDNhanVien, DiaChi, Ho, NgaySinh, SDT, Ten)
  values ('nv1',N'Bình Thạnh',N'Nguyễn Thành','1998-12-25','0905530728',N'Luân')

  insert into NhanVien(IDNhanVien, DiaChi, Ho, NgaySinh, SDT, Ten)
  values ('nv2',N'Gò Vấp',N'Nguyễn Đồng','1998-1-2','0905530728',N'Hành')

   insert into NhanVien(IDNhanVien, DiaChi, Ho, NgaySinh, SDT, Ten)
  values ('nv3',N'Pastuer, Quận 1',N'Trần Quang',N'1995-1-2','0906653594',N'Tuấn')
   insert into NhanVien(IDNhanVien, DiaChi, Ho, NgaySinh, SDT, Ten)
  values ('nv4',N'Hà Huy Giáp, Gò Vấp',N'Nguyễn Thiện',N'1992-1-2','0977696868',N'Thuật')
   insert into NhanVien(IDNhanVien, DiaChi, Ho, NgaySinh, SDT, Ten)
  values ('nv5',N'Nguyễn Thị Minh Khai, Quận 1',N'Trần Quang',N'1995-1-2','0906124851',N'Đại')



  insert into TaiKhoan(IDnhanVien, Loai, Matkhau, NgayBatDau, Username)
  values ('nv3', N'Nhân viên nhận bệnh', '123','12-3-2017','nvnb1')

  insert into TaiKhoan(IDnhanVien, Loai, Matkhau, NgayBatDau, Username)
  values ('nv1', N'Bác Sỹ', '456','12-3-2017','bs1')

  insert into TaiKhoan(IDnhanVien, Loai, Matkhau, NgayBatDau, Username)
  values ('nv2', N'Bác Sỹ', 'hihi','1-1-2015','bs2')
  insert into TaiKhoan(IDnhanVien, Loai, Matkhau, NgayBatDau, Username)
  values ('nv4', N'Nhân Viên Phát Thuốc', '123','1-1-2015','nvpt1')
  insert into TaiKhoan(IDnhanVien, Loai, Matkhau, NgayBatDau, Username)
  values ('nv5', N'Quản Lý', 'haha','1-1-2015','ql1')


   /* insert into BenhAn (IdBenhAn, NgayLap, TenBenh, IDBenhNhan, IDNhanVien) 
  values (1,null,null,'bn1','nv2')*/

  insert into BenhAn (IdBenhAn, NgayLap, TenBenh, IDBenhNhan, IDNhanVien) 
  values (3,'2018-11-2',null,'benhnhan1','nv2')
 
    insert into Thuoc(IDThuoc, DonGia, TenThuoc)
  values ('thuoc1',20000,'Permarin')
  insert into Thuoc(IDThuoc, DonGia ,TenThuoc)
  values ('thuoc2',19500,'Panadol')


   insert into ToaThuoc(IDToaThuoc, GhiChu, TrangThai, IDBenhAn)
  values ('toa1',N'1 lần 1 viên', 0, 3)

  insert into [dbo].[ChiTietKhoThuoc](IDThuoc, IDToaThuoc, SoLuong)
  values ('thuoc1','toa1', 10)

 select * from BenhAn
select * from BenhNhan
select * from [dbo].[NhanVien]
select * from taikhoan
 select * from BenhAn
select * from BenhNhan
select * from ToaThuoc
select * from ChiTietKhoThuoc
select * from ToaThuoc
select * from DonXetNghiem

select * from BenhNhan c where c.Ho like N'%Trần Văn%' and c.TenBN like N'%Hay%'
select * from BenhNhan c where  c.TenBN like N'%Bủm%'
select bn.IDBenhNhan, bn.DiaChi, bn.Ho, bn.SDT, bn.TenBN from ToaThuoc tt inner join BenhAn ba on tt.IdBenhAn=ba.IdBenhAn inner join BenhNhan bn on ba.IDBenhNhan = bn.IDBenhNhan
where tt.IDToaThuoc like 'Toa1'

select * from BenhNhan bn
where bn.Ho like N'Trần'
  


