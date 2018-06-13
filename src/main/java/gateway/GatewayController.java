package gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

	private List<Gateway> gateways = new ArrayList<Gateway>();

	@PutMapping("/gateway")
	public ResponseEntity<Void> saveGateway(@RequestBody Gateway gateway) {
		if (validateIPv4(gateway.getIp())) {
			gateways.add(gateway);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/gateway/all")
	public ResponseEntity<List<Gateway>> getAllGateways() {
		return new ResponseEntity<List<Gateway>>(gateways, HttpStatus.OK);
	}

	@GetMapping("/gateway/{id}")
	public ResponseEntity<Gateway> getById(@PathVariable Integer id) {
		for (Gateway gateway : gateways) {
			if (id.equals(gateway.getUid())) {
				return new ResponseEntity<Gateway>(gateway, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/gateway/{id}/device")
	public ResponseEntity<Void> addDeviceToGateway(@PathVariable Integer id, @RequestBody Device device) {
		for (Gateway gateway : gateways) {
			if (id.equals(gateway.getUid())) {
				if (gateway.getDevices().size() < 10) {
					gateway.getDevices().add(device);
					return ResponseEntity.ok().build();
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/gateway/{id}/device/{deviceId}")
	public ResponseEntity<Void> removeDeviceFromGateway(@PathVariable Integer id, @PathVariable Integer deviceId) {
		for (Gateway gateway : gateways) {
			if (id.equals(gateway.getUid())) {
				if (gateway.getDevices() != null) {
					Device deviceToDelete = null;
					for (Device device : gateway.getDevices()) {
						if (deviceId.equals(device.getUid())) {
							deviceToDelete = device;
						}
					}
					if (deviceToDelete != null) {
						gateway.getDevices().remove(deviceToDelete);
						return ResponseEntity.ok().build();
					}
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public boolean validateIPv4(String ip) {
		Pattern p = Pattern.compile("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b");
		Matcher m = p.matcher(ip);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
}
