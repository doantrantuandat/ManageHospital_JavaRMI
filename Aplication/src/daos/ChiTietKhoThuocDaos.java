package daos;

import java.util.List;

import htqlbv_entities.ChiTietKhoThuoc;

public class ChiTietKhoThuocDaos extends GeneralCRUD<ChiTietKhoThuoc> {
	@SuppressWarnings("unchecked")
	public List<ChiTietKhoThuoc> GetChiTietKhoThuocTheoToa(String matoa)
	{
		String s = "select ct.* from ToaThuoc t inner join ChiTietKhoThuoc ct on t.IDToaThuoc = ct.IDToaThuoc inner join Thuoc tt on tt.IDThuoc = ct.IDThuoc where t.IDToaThuoc = '"+matoa+"'";
		return manager.createNativeQuery(s,ChiTietKhoThuoc.class).getResultList();
				
	}
	@SuppressWarnings("unchecked")
	public List<ChiTietKhoThuoc> getSoLuongTheoID(String matoa, String maThuoc) {
		String s = "select ct.IDThuoc, ct.IDToaThuoc, ct.SoLuong from [dbo].[ChiTietKhoThuoc] ct inner join [dbo].[ToaThuoc] tt on ct.IDToaThuoc=tt.IDToaThuoc inner join [dbo].[Thuoc] t on ct.IDThuoc=t.IDThuoc where ct.IDThuoc='"
				+ maThuoc + "' and ct.IDToaThuoc='" + matoa + "'";
		return manager.createNativeQuery(s, ChiTietKhoThuoc.class).getResultList();
	}
}
