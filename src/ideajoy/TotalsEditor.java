package ideajoy;

import java.io.File;
import java.io.FileInputStream;

import com.garmin.fit.Decode;
import com.garmin.fit.FileEncoder;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.TotalsMesg;
import com.garmin.fit.FileIdMesgListener;
import com.garmin.fit.Fit;
import com.garmin.fit.TotalsMesgListener;


public class TotalsEditor {

	MesgListener listener;
	
	private void decode(File file) throws Exception{
		Decode decode = new Decode();
		//decode.skipHeader();        // Use on streams with no header and footer (stream contains FIT defn and data messages only)
        //decode.incompleteStream();  // This suppresses exceptions with unexpected eof (also incorrect crc)
        MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);
        listener = new MesgListener();
        
        mesgBroadcaster.addListener((FileIdMesgListener)listener);
        mesgBroadcaster.addListener((TotalsMesgListener)listener);
        
        FileInputStream in = new FileInputStream(file);
        decode.read(in, mesgBroadcaster, mesgBroadcaster);
	}
	
	
	private void encode(File file) throws Exception{
		FileEncoder encode = new FileEncoder(file, Fit.ProtocolVersion.V2_0);
		encode.write(listener.fileIdMesg);
		for(TotalsMesg total : listener.totals){
			encode.write(total);
		}
		encode.close();
	}
	
	private void edit(String[] args) {
		for(int i=0;i<args.length;i+=2){
			add(Integer.parseInt(args[i]),Long.parseLong(args[i+1]));
			add(0,Long.parseLong(args[i+1]));
		}	
	}
	
	private void add(int index, long meters){
		TotalsMesg total = listener.totals.get(index);
		total.setDistance(total.getDistance() + meters);
	}
	
	public static void main(String[] args) throws Exception{
		TotalsEditor editor = new TotalsEditor();
		
		editor.decode( new File("Totals.fit"));
		editor.edit(args);
		editor.encode(new File("edit/Totals.fit"));
	}


	
}
