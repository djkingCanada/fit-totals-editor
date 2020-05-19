package ideajoy;

import java.util.ArrayList;
import java.util.List;

import com.garmin.fit.DeveloperDataIdMesg;
import com.garmin.fit.DeveloperDataIdMesgListener;
import com.garmin.fit.DeveloperFieldDescription;
import com.garmin.fit.DeveloperFieldDescriptionListener;
import com.garmin.fit.DeviceInfoMesg;
import com.garmin.fit.DeviceInfoMesgListener;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.FileIdMesgListener;
import com.garmin.fit.MonitoringMesg;
import com.garmin.fit.MonitoringMesgListener;
import com.garmin.fit.RecordMesg;
import com.garmin.fit.RecordMesgListener;
import com.garmin.fit.TotalsMesg;
import com.garmin.fit.TotalsMesgListener;
import com.garmin.fit.UserProfileMesg;
import com.garmin.fit.UserProfileMesgListener;

public class MesgListener implements FileIdMesgListener, TotalsMesgListener{

	FileIdMesg fileIdMesg;
	List<TotalsMesg> totals = new ArrayList<TotalsMesg>();
	DeveloperDataIdMesg developerDataId;

	@Override
	public void onMesg(FileIdMesg mesg) {
		fileIdMesg = mesg;
		
	}

	@Override
	public void onMesg(TotalsMesg mesg) {
		if(mesg.getDistance()>0){
			System.out.println(totals.size() + " " + mesg.getDistance());
		}
		totals.add(mesg);
	}


}
