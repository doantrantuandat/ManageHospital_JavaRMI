package daos;

import java.util.ArrayList;
import java.util.List;

import htqlbv_entities.ChiTietKhoThuoc;
import htqlbv_entities.NhanVien;
import htqlbv_entities.ToaThuoc;

public class NhanVienPhatThuocDaos {

	private ToaThuocDaos toaThuocDaos;
	private ChiTietKhoThuocDaos chiTietKhoThuocDaos;
	private List<ToaThuoc> dstt;
	private TaiKhoanDaos taiKhoanDaos;
	public NhanVienPhatThuocDaos() {
		taiKhoanDaos = new TaiKhoanDaos();
		toaThuocDaos = new ToaThuocDaos();
		chiTietKhoThuocDaos = new ChiTietKhoThuocDaos();
		new ThuocDaos();

	}
	public List<ToaThuoc> GetListToaThuocChuaPhat() {
		List<ToaThuoc> a = toaThuocDaos.getAll(ToaThuoc.class);
		dstt = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			if(!a.get(i).isTrangThai() == true )
			{
				dstt.add(a.get(i));
			}
		}
		return dstt;
	}
	public Double TinhDonGiaToaThuoc(String matoathuoc) {
		return toaThuocDaos.DonGiaToaThuocTheoID(matoathuoc);
	}
	public List<ChiTietKhoThuoc> getChiTietKhoThuoc(String matoa) {
		return chiTietKhoThuocDaos.GetChiTietKhoThuocTheoToa(matoa);
	}
	public boolean CapNhatTrangThaiToaThuoc(ToaThuoc t) {
		try {
			toaThuocDaos.Sua(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean CheckMatKhauCu(NhanVien nhanVien,String mkc)
	{
		try {
			System.out.println(taiKhoanDaos.CheckMatKhauCu(nhanVien));
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
