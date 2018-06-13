package gateway;

import java.util.List;

public class Gateway {

	private Integer uid;

	private String readableName;

	private String ip;

	private List<Device> devices;

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

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "Gateway [uid=" + uid + ", readableName=" + readableName + ", ip=" + ip + ", devices=" + devices + "]";
	}
	
	
}
