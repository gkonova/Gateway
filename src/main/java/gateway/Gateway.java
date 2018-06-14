package gateway;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Gateway {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uid;

	private String readableName;

	private String ip;

	private Set<Device> devices;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getReadableName() {
		return readableName;
	}

	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL)
	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "Gateway [uid=" + uid + ", readableName=" + readableName + ", ip=" + ip + ", devices=" + devices + "]";
	}

}
