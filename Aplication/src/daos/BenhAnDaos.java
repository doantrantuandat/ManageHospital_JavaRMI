package daos;

import java.util.List;

import htqlbv_entities.BenhAn;


public class BenhAnDaos extends GeneralCRUD<BenhAn>{
	public BenhAn layBenhAnTheoID(int id) {
		return manager.find(BenhAn.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<BenhAn> layBenhAntheoIDBenhNhan(String id) {
		return manager.createNativeQuery("select * from BenhAn where IDBenhNhan = '"+id+"' and TenBenh is null ",
				BenhAn.class).getResultList();
	}
}
