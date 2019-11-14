package daos;

import java.util.ArrayList;
import java.util.List;

import daos.BenhAnDaos;
import daos.ThuocDaos;
import htqlbv_entities.BenhAn;
import htqlbv_entities.BenhNhan;
import htqlbv_entities.NhanVien;
import htqlbv_entities.Thuoc;

public class BacSiDaos {
	private List<BenhAn> dsba;
	private List<Thuoc> dsThuoc;
	private BenhAnDaos benhAnDaos;
	private ThuocDaos thuocDaos;
	private TaiKhoanDaos taiKhoanDaos;

	public BacSiDaos() {
		dsba = new ArrayList<>();
		dsThuoc = new ArrayList<>();
		benhAnDaos = new BenhAnDaos();
		thuocDaos = new ThuocDaos();
		taiKhoanDaos = new TaiKhoanDaos();

	}

	public List<BenhAn> LayBenhAnTuSQL() {
		dsba = benhAnDaos.getAll(BenhAn.class);
		return dsba;
	}

	public List<BenhNhan> LayBenhNhan() {

		List<BenhNhan> dsbenhNhan = new ArrayList<>();
		List<BenhAn> list = LayBenhAnTuSQL();
		for (BenhAn benhAn : list) {
			BenhNhan bn = new BenhNhan();
			bn = benhAn.getBenhNhan();
			dsbenhNhan.add(bn);
		}
		return dsbenhNhan;

	}

	public boolean SuaBenhAn(BenhAn newValue) {

		benhAnDaos.Sua(newValue);
		return true;
	}

	public List<Thuoc> LayThuocTuSQL() {
		dsThuoc = thuocDaos.getAll(Thuoc.class);
		return dsThuoc;
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

