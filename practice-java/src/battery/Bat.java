package battery;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Bat {
	public static int getNumber(final String ptn, final BufferedReader br) throws IOException {
		for(String s = br.readLine(); s != null; s = br.readLine()){
			Matcher m = Pattern.compile(ptn + "\\s*(\\d+).*").matcher(s);
			if(m.matches())	return Integer.parseInt(m.group(1));
		}
		return -1;
	}
	
	public static int getCapacitence(String path, String ptn){
		BufferedReader state = null;
		int result = -1;
		try {
			state = new BufferedReader(new FileReader(path));
			result = getNumber(ptn,state);
			state.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		int full = getCapacitence("/proc/acpi/battery/BAT0/info", "last full capacity:");
		int now = getCapacitence("/proc/acpi/battery/BAT0/state", "remaining capacity:");
		System.out.format("%.0f%%%n", now * 100.0 / full);
	}

}
