package aplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import daos.BenhAnDaos;
import daos.BenhNhanDaos;
import daos.ChiTietKhoThuocDaos;
import daos.NhanVienDaos;
import daos.TaiKhoanDaos;
import daos.ThuocDaos;
import daos.ToaThuocDaos;
import gui.Gui_BacSi;
import gui.Gui_DangNhap;
import gui.Gui_NhanVienNhanBenh;
import gui.Gui_NhanVienPhatThuoc;
import gui.Gui_QuanLy;
import htqlbv_entities.BenhAn;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.ChiTietKhoThuoc;
import htqlbv_entities.NhanVien;
import htqlbv_entities.TaiKhoan;
import htqlbv_entities.Thuoc;
import htqlbv_entities.ToaThuoc;

public class testing {
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		new Gui_DangNhap();
/**
 * Thêm dữ liệu vào database bằng tay :(((
 */
///////////////////////////////////////////////////////////////////////////		
//		NhanVienDaos nhanVienDaos = new NhanVienDaos();
//		TaiKhoanDaos taiKhoanDaos = new TaiKhoanDaos();
//		BenhAnDaos benhAnDaos = new BenhAnDaos();
//		ToaThuocDaos toaThuocDaos = new ToaThuocDaos();
//		BenhNhanDaos benhNhanDaos = new BenhNhanDaos();
//		ChiTietKhoThuocDaos chiTietKhoThuocDaos = new ChiTietKhoThuocDaos();
//		ThuocDaos thuocDaos = new ThuocDaos();
//		
//		NhanVien nhanVien1 = new NhanVien("NV01","Đăng Khoa" , "Nguyễn Trần", "Bình Thạnh", "0321456987", LocalDate.of(1975, 2, 26));
//		TaiKhoan taiKhoan1 = new TaiKhoan("TK01", "Nhân Viên Nhận Bệnh", "NVNB1", "NVNB1", LocalDate.of(2017, 04, 11)) ;
//		
//		nhanVien1.setTaiKhoan(taiKhoan1);
//		taiKhoan1.setNhanVien(nhanVien1);
//		
//		NhanVien nhanVien2 = new NhanVien("NV02","Đăng Khoa" , "Nguyễn", "Tân Bình", "0397026798", LocalDate.of(1998,7, 26));
//		TaiKhoan taiKhoan2 = new TaiKhoan("TK02", "Bác Sỹ", "BS1", "BS1", LocalDate.of(2017, 04, 11)) ;
//		
//		nhanVien2.setTaiKhoan(taiKhoan2);
//		taiKhoan2.setNhanVien(nhanVien2);
//		
//		NhanVien nhanVien3 = new NhanVien("NV03","Thành Luân" , "Nguyễn", "Bình Thạnh", "0321456987", LocalDate.of(1965, 2, 26));
//		TaiKhoan taiKhoan3 = new TaiKhoan("TK03", "Bác Sỹ", "BS2", "BS2", LocalDate.of(2017, 05, 11)) ;
//		
//		nhanVien3.setTaiKhoan(taiKhoan3);
//		taiKhoan3.setNhanVien(nhanVien3);
//		
//		NhanVien nhanVien4 = new NhanVien("NV04","Thanh Phương" , "Nguyễn Đặng", "Bình Thạnh", "0214569852", LocalDate.of(1995, 2, 26));
//		TaiKhoan taiKhoan4 = new TaiKhoan("TK04", "Bác Sỹ", "BS3", "BS3", LocalDate.of(2017, 04, 11)) ;
//		
//		nhanVien4.setTaiKhoan(taiKhoan4);
//		taiKhoan4.setNhanVien(nhanVien4);
//
//		NhanVien nhanVien5 = new NhanVien("NV05", "Công", "Phan Chí", "Quận 2", "0321455216", LocalDate.of(1966, 2, 11));
//		TaiKhoan taiKhoan5 = new TaiKhoan("TK05", "Nhân Viên Nhận Bệnh", "NVNB2", "NVNB2", LocalDate.of(2000, 2, 11));
//		
//		nhanVien5.setTaiKhoan(taiKhoan5);
//		taiKhoan5.setNhanVien(nhanVien5);
//		
//		NhanVien nhanVien6 = new NhanVien("NV06", "Lợi", "Võ Ích", "Quận 5", "032564852", LocalDate.of(1998, 02, 11));
//		TaiKhoan taiKhoan6 = new TaiKhoan("TK06", "Nhân Viên Phát Thuốc", "NVPT1", "NVPT1", LocalDate.of(2002, 2, 11));
//
//		nhanVien6.setTaiKhoan(taiKhoan6);
//		taiKhoan6.setNhanVien(nhanVien6);
//		
//		NhanVien nhanVien7 = new NhanVien("NV07", "Lôc", "Tạ Thiên", "Quận 5", "0321563214", LocalDate.of(1998, 07, 31));
//		TaiKhoan taiKhoan7 = new TaiKhoan("TK07", "Quản Lý", "QL1", "QL1", LocalDate.of(2006, 2, 11));
//		
//		nhanVien7.setTaiKhoan(taiKhoan7);
//		taiKhoan7.setNhanVien(nhanVien7);
//		
//		nhanVienDaos.Them(nhanVien1);
//		taiKhoanDaos.Them(taiKhoan1);
//		nhanVienDaos.Them(nhanVien2);
//		taiKhoanDaos.Them(taiKhoan2);
//		nhanVienDaos.Them(nhanVien3);
//		taiKhoanDaos.Them(taiKhoan3);
//		nhanVienDaos.Them(nhanVien4);
//		taiKhoanDaos.Them(taiKhoan4);
//		nhanVienDaos.Them(nhanVien5);
//		taiKhoanDaos.Them(taiKhoan5);
//		nhanVienDaos.Them(nhanVien6);
//		taiKhoanDaos.Them(taiKhoan6);
//		nhanVienDaos.Them(nhanVien7);
//		taiKhoanDaos.Them(taiKhoan7);
//		
//		BenhNhan benhNhan1 = new BenhNhan("bn1", "Thành An", "Thái", "0236541235", "Gò Vấp");
//		BenhNhan benhNhan2 = new BenhNhan("bn2","Nhật Quang","Nguyễn Lê","0321564235","Gò Vấp");
//		BenhNhan benhNhan3 = new BenhNhan("bn3", "Tấn Lộc", "Trần", "0325648624", "Gò Vấp");
//		
//		benhNhanDaos.Them(benhNhan1);
//		benhNhanDaos.Them(benhNhan2);
//		benhNhanDaos.Them(benhNhan3);
//		
//		ToaThuoc toaThuoc1 = new ToaThuoc("Toa1", "asdas", false);
//		ToaThuoc toaThuoc2 = new ToaThuoc("Toa2", "asdas", false);
//		ToaThuoc toaThuoc3 = new ToaThuoc("Toa3", "asdas", false);
//		
//		BenhAn benhAn1 = new BenhAn(LocalDate.now(), null);
//		benhAn1.setBenhNhan(benhNhan2);
//		benhAn1.setNhanVien(nhanVien2);
//		toaThuoc1.setBenhAn(benhAn1);
//		
//		BenhAn benhAn2 = new BenhAn(LocalDate.now(), null);
//		benhAn2.setBenhNhan(benhNhan2);
//		benhAn2.setNhanVien(nhanVien4);
//		toaThuoc2.setBenhAn(benhAn2);
//		
//		BenhAn benhAn3 = new BenhAn(LocalDate.now(), null);
//		benhAn3.setBenhNhan(benhNhan3);
//		benhAn3.setNhanVien(nhanVien2);
//		toaThuoc3.setBenhAn(benhAn3);
//
//		benhAnDaos.Them(benhAn1);
//		benhAnDaos.Them(benhAn2);
//		benhAnDaos.Them(benhAn3);
//		toaThuocDaos.Them(toaThuoc1);
//		toaThuocDaos.Them(toaThuoc2);
//		toaThuocDaos.Them(toaThuoc3);
//
//		Thuoc thuoc1 = new Thuoc(10000, "TH1", "Thuốc Cảm", 1000);
//		Thuoc thuoc2 = new Thuoc(20000, "TH2", "Thuốc Ho", 1000);
//		Thuoc thuoc3 = new Thuoc(30000, "TH3", "Thuốc Giảm Đau", 1000);
//		Thuoc thuoc4 = new Thuoc(40000, "TH4", "Thuốc Hạ Sốt", 1000);
//		Thuoc thuoc5 = new Thuoc(60000, "TH5", "Thuốc Tránh Thai", 1000);
//		Thuoc thuoc6 = new Thuoc(60000, "TH6", "Thuốc Thận", 1000);
//		Thuoc thuoc7 = new Thuoc(50000, "TH7", "Thuốc Gan", 1000);
//		Thuoc thuoc8 = new Thuoc(50000, "TH8", "Thuốc Phổi", 1000);
//		
//		ChiTietKhoThuoc chiTietKhoThuoc1 = new ChiTietKhoThuoc(toaThuoc1, thuoc1, 3);
//		ChiTietKhoThuoc chiTietKhoThuoc2 = new ChiTietKhoThuoc(toaThuoc2, thuoc2, 3);
//		ChiTietKhoThuoc chiTietKhoThuoc3 = new ChiTietKhoThuoc(toaThuoc3, thuoc3, 3);
//
//		thuocDaos.Them(thuoc1);
//		thuocDaos.Them(thuoc2);
//		thuocDaos.Them(thuoc3);
//		thuocDaos.Them(thuoc4);
//		thuocDaos.Them(thuoc5);
//		thuocDaos.Them(thuoc6);
//		thuocDaos.Them(thuoc7);
//		thuocDaos.Them(thuoc8);
//		
//		
//		chiTietKhoThuocDaos.Them(chiTietKhoThuoc1);
//		chiTietKhoThuocDaos.Them(chiTietKhoThuoc2);
//		chiTietKhoThuocDaos.Them(chiTietKhoThuoc3);

/////////////////////////////////////////////////////////////////////////////////////////

		
}
}

