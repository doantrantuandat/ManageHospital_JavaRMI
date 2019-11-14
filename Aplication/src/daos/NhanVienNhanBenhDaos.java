package daos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import htqlbv_entities.BenhAn;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.NhanVien;

public class NhanVienNhanBenhDaos {

	private List<BenhNhan> dsbn ;
	private BenhNhanDaos benhNhanDaos;
	private NhanVienDaos nhanVienDaos;
	private BenhAnDaos benhAnDaos;
	private TaiKhoanDaos taiKhoanDaos;

	public NhanVienNhanBenhDaos() {
		dsbn = new ArrayList<>();
		benhNhanDaos = new BenhNhanDaos();
		nhanVienDaos = new NhanVienDaos();
		benhAnDaos = new BenhAnDaos();
		taiKhoanDaos = new TaiKhoanDaos();
	}
	
	public List<BenhNhan> LayBenhNhanTuSQL ()
	{
		dsbn = benhNhanDaos.getAll(BenhNhan.class);
		return dsbn;
	}
	public boolean ThemBenhNhanVaoDataBase(BenhNhan benhNhan)
	{
		if(dsbn.contains(benhNhan))
		{
			JOptionPane.showMessageDialog(new JFrame(),"Đã tồn tại bệnh nhân trong danh sách");
			return false;
		}
		else {
			benhNhanDaos.Them(benhNhan);
			JOptionPane.showMessageDialog(new JFrame(),"Thêm thành công");
			return true;
		}
	}
	public boolean SuaBenhNhanVaoDatabase(BenhNhan benhNhan)
	{
		try {
			benhNhanDaos.Sua(benhNhan);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		}
	}
	public List<BenhNhan> TimBenhNhanTheoTen(String tenbn) {
		return benhNhanDaos.TimTheoTen(tenbn);
	}
	public List<BenhNhan> TimBenhNhanTheoSDT(String sdt) {
		return benhNhanDaos.TimTheoSDT(sdt);
	}
	public List<NhanVien> GetBacSy()
	{
		return nhanVienDaos.getBacSy();
	}
	public boolean ChuyenBenhNhanQuaBacSy(NhanVien nhanVien, BenhNhan benhNhan)
	{
		try {
			BenhAn benhAn = new BenhAn();
			nhanVien.setBenhAn(Arrays.asList(benhAn));
			benhNhan.setBenhAns(Arrays.asList(benhAn));
			
			benhAn.setBenhNhan(benhNhan);
			benhAn.setNgayLap(LocalDate.now());
			benhAn.setNhanVien(nhanVien);
			benhAn.setToaThuoc(null);
			benhAn.setDonXetNghiems(null);
			
			benhAnDaos.Them(benhAn);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean CheckMatKhauCu(NhanVien nhanVien,String mkc)
	{
		try {
			if(taiKhoanDaos.CheckMatKhauCu(nhanVien).equalsIgnoreCase(mkc))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean DoiMatKhau(NhanVien nhanVien,String mkm)
	{
		return taiKhoanDaos.DoiMatKhau(nhanVien, mkm);
	}

}
