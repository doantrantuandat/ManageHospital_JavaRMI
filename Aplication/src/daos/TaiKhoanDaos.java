package daos;

import java.util.List;

import javax.persistence.EntityTransaction;

import htqlbv_entities.NhanVien;
import htqlbv_entities.TaiKhoan;

public class TaiKhoanDaos extends GeneralCRUD<TaiKhoan> {
	@SuppressWarnings("unchecked")
	public String CheckMatKhauCu(NhanVien nhanVien)
	{
		String query = "select b.* from NhanVien a inner join TaiKhoan b on a.IDNhanVien=b.IDNhanVien where a.IDNhanVien like '"+nhanVien.getIDNhanVien()+"'";
		List<TaiKhoan> taiKhoans = manager.createNativeQuery(query,TaiKhoan.class).getResultList();
		return taiKhoans.get(0).getMatkhau();
	}
	public boolean DoiMatKhau(NhanVien nhanVien, String mkm)
	{
		EntityTransaction transaction = manager.getTransaction();
		String query = "update TaiKhoan set Matkhau = '"+mkm+"' where IDNhanVien = '"+nhanVien.getIDNhanVien()+"'";
		transaction.begin();
		int a = manager.createNativeQuery(query).executeUpdate();
		transaction.commit();
		return a>0;
	}
	public boolean xoaTheoID(String id) {
		TaiKhoan nhanVien = manager.find(TaiKhoan.class, id);
		if (nhanVien != null) {
			manager.getTransaction().begin();
	        manager.remove(nhanVien);
	        manager.getTransaction().commit();
	        return true;
		}
		return false;
	}
}
