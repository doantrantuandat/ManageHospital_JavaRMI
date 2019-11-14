package daos;

import java.util.List;

import htqlbv_entities.BenhAn;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.NhanVien;
import htqlbv_entities.TaiKhoan;
import htqlbv_entities.Thuoc;
import htqlbv_entities.ToaThuoc;

public class QuanLyDaos {
	private TaiKhoanDaos taiKhoanDaos;
	private NhanVienDaos nhanVienDaos;
	private ToaThuocDaos toaThuocDaos;
	private ThuocDaos thuocDaos;
	private ChiTietKhoThuocDaos chiTietKhoThuocDaos;
	private BenhNhanDaos benhNhanDaos;
	private BenhAnDaos benhAnDaos;

	public QuanLyDaos() {
		taiKhoanDaos = new TaiKhoanDaos();
		nhanVienDaos = new NhanVienDaos();
		toaThuocDaos = new ToaThuocDaos();
		thuocDaos = new ThuocDaos();
		chiTietKhoThuocDaos = new ChiTietKhoThuocDaos();
		benhNhanDaos = new BenhNhanDaos();
		benhAnDaos = new BenhAnDaos();
	}

	public List<BenhNhan> getAllBenhNhan() {
		return benhNhanDaos.getAll(BenhNhan.class);
	}

	public List<NhanVien> getAllNhanVien() {
		return nhanVienDaos.getAll(NhanVien.class);
	}

	public List<TaiKhoan> getAllTaiKhoan() {
		return taiKhoanDaos.getAll(TaiKhoan.class);
	}

	public List<Thuoc> getAllThuoc() {
		return thuocDaos.getAll(Thuoc.class);
	}

	public List<ToaThuoc> getAllToaThuoc() {
		return toaThuocDaos.getAll(ToaThuoc.class);
	}

	public double getTongGiaToaThuocTheoID(List<ToaThuoc> dsToaThuoc, int i) {
		return toaThuocDaos.DonGiaToaThuocTheoID(dsToaThuoc.get(i).getIDToaThuoc());
	}

	public boolean xoaNhanVienTheoID(String id) {
		if (nhanVienDaos.xoaTheoID(id)) {
			return true;
		}
		return false;
	}

	public boolean themNhanVien(NhanVien nhanVien) {
		if (nhanVienDaos.Them(nhanVien))
			return true;
		return false;
	}

	public boolean suaThongTinNhanVien(NhanVien nhanVien) {
		if (nhanVienDaos.Sua(nhanVien))
			return true;
		return false;
	}

	public NhanVien getNhanVienTheoID(String id) {
		return nhanVienDaos.getTheoID(id);
	}

	public boolean themTaiKhoan(TaiKhoan taiKhoan) {
		if (taiKhoanDaos.Them(taiKhoan))
			return true;
		return false;
	}

	public boolean suaThongTinTaiKhoan(TaiKhoan taiKhoan) {
		if (taiKhoanDaos.Sua(taiKhoan))
			return true;
		return false;
	}

	public boolean xoaTaiKhoanTheoID(String id) {
		if (taiKhoanDaos.xoaTheoID(id))
			return true;
		return false;
	}

	public List<NhanVien> timNhanVienTheoID(String id) {
		return nhanVienDaos.timTheoID(id);
	}

	public List<NhanVien> timNhanVienTheoTen(String ten) {
		return nhanVienDaos.timTheoTen(ten);
	}

	public List<NhanVien> timNhanVienTheoSDT(String sdt) {
		return nhanVienDaos.timTheoTen(sdt);
	}

	public List<BenhAn> getAllBenhAn() {
		return benhAnDaos.getAll(BenhAn.class);
	}

	public int getNamLapBenhAn(List<BenhAn> benhAns, int i) {
		return benhAns.get(i).getNgayLap().getYear();
	}

	public void xoaBenhNhanTheoID(String id) {
		benhNhanDaos.xoaTheoID(id);
	}

	public List<Thuoc> getThuocTheoToa(String id) {
		return thuocDaos.getThuocTheoToa(id);
	}

	public int getSoLuongThuoc(String maToa, String maThuoc) {
		return chiTietKhoThuocDaos.getSoLuongTheoID(maToa, maThuoc).get(0).getSoLuong();
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
