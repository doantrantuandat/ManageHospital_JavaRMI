package htqlbv_entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BenhNhan {
	@Id
	private String IDBenhNhan;
	@Column(columnDefinition = "nvarchar(50)")
	private String TenBN;
	@Column(columnDefinition = "nvarchar(50)")
	private String Ho;
	@Column(columnDefinition = "nvarchar(50)")
	private String SDT;
	@Column(columnDefinition = "nvarchar(50)")
	private String DiaChi;
	
	@OneToMany(mappedBy = "benhNhan",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BenhAn> benhAns;
	
	
	public BenhNhan() {
		super();
	}
	public BenhNhan(String iDBenhNhan, String tenBN, String ho, String sDT, String diaChi) {
		super();
		IDBenhNhan = iDBenhNhan;
		TenBN = tenBN;
		Ho = ho;
		SDT = sDT;
		DiaChi = diaChi;
	}
	public String getIDBenhNhan() {
		return IDBenhNhan;
	}
	public void setIDBenhNhan(String iDBenhNhan) {
		IDBenhNhan = iDBenhNhan;
	}
	public String getTenBN() {
		return TenBN;
	}
	public void setTenBN(String tenBN) {
		TenBN = tenBN;
	}
	public String getHo() {
		return Ho;
	}
	public void setHo(String ho) {
		Ho = ho;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	
	public List<BenhAn> getBenhAns() {
		return benhAns;
	}
	public void setBenhAns(List<BenhAn> benhAns) {
		this.benhAns = benhAns;
	}
	@Override
	public String toString() {
		return "BenhNhan [IDBenhNhan=" + IDBenhNhan + ", TenBN=" + TenBN + ", Ho=" + Ho + ", SDT=" + SDT + ", DiaChi="
				+ DiaChi + "]";
	}
	

	
}
