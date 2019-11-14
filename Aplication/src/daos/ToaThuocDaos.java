package daos;

import htqlbv_entities.ToaThuoc;

public class ToaThuocDaos extends GeneralCRUD<ToaThuoc>{
	public double DonGiaToaThuocTheoID(String matoathuoc) {
		String a = "select sum([DonGia]*[SoLuong]) from ToaThuoc t inner join ChiTietKhoThuoc ct on t.IDToaThuoc = ct.IDToaThuoc inner join Thuoc tt on tt.IDThuoc = ct.IDThuoc  where t.IDToaThuoc = '"+matoathuoc+"' group by t.[IDToaThuoc]";
		Double b =  Double.valueOf(manager.createNativeQuery(a).getSingleResult().toString());
		return b;
	}
	public ToaThuoc layToaThuocTheoID(String id) {
		return manager.find(ToaThuoc.class, id);
	}
}
