package daos;

import java.util.List;

import htqlbv_entities.NhanVien;

public class NhanVienDaos extends GeneralCRUD<NhanVien>{
	@SuppressWarnings("unchecked")
	public List<NhanVien> getBacSy()
	{
		return manager.createNativeQuery("select a.* from NhanVien a inner join TaiKhoan b on a.IDNhanVien = b.IDNhanVien where b.Loai like N'Bác Sỹ'",NhanVien.class).getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> getNhanVien(String username,String password)
	{
		return  manager.createNativeQuery("select a.* from NhanVien a inner join TaiKhoan b on a.IDNhanVien=b.IDNhanVien where b.Username like '"+username+"' and b.Matkhau like '"+password+"'",NhanVien.class).getResultList();
	}
	public NhanVien getTheoID(String id) {
		return manager.find(NhanVien.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<NhanVien> timTheoID(String id) {
		String s = "select * from [dbo].[NhanVien] where [IDNhanVien] = '" + id + "'";
		return manager.createNativeQuery(s, NhanVien.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<NhanVien> timTheoTen(String ten) {
		String s = "select * from [dbo].[NhanVien] where [Ten] = '" + ten + "'";
		return manager.createNativeQuery(s, NhanVien.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<NhanVien> timTheoSDT(String SDT) {
		String s = "select * from [dbo].[NhanVien] where [SDT] = '" + SDT + "'";
		return manager.createNativeQuery(s, NhanVien.class).getResultList();
	}

	public boolean xoaTheoID(String id) {
		NhanVien nhanVien = manager.find(NhanVien.class, id);
		if (nhanVien != null) {
			manager.getTransaction().begin();
	        manager.remove(nhanVien);
	        manager.getTransaction().commit();
	        return true;
		}
		return false;
	}
}
