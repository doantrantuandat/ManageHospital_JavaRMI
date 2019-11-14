package daos;


import java.util.List;

import htqlbv_entities.Thuoc;

public class ThuocDaos extends GeneralCRUD<Thuoc>{
	public Thuoc GetbyID(String id)
	{
		return manager.find(Thuoc.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Thuoc> getThuocTheoToa(String matoa) {
		String s = "select tt.* from ToaThuoc t inner join ChiTietKhoThuoc ct on t.IDToaThuoc = ct.IDToaThuoc inner join Thuoc tt on tt.IDThuoc = ct.IDThuoc where t.IDToaThuoc = '"
				+ matoa + "'";
		return manager.createNativeQuery(s, Thuoc.class).getResultList();
	}
}
