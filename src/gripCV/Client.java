package gripCV;

import java.util.ArrayList;
import java.util.List;

import gripCV.GripVII;

import org.opencv.core.*;
import org.opencv.core.Core.*;
//import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import org.opencv.videoio.VideoCapture;

public class Client{
	public static void main(String[] args)
	{
		GripVII locator = new GripVII();
		VideoCapture camera = new VideoCapture(1);
		Mat adragna = new Mat();
		while (true)
		{
			camera.read(adragna);
			locator.process(adragna);
			MatOfKeyPoint bob = locator.findBlobsOutput();
			List<KeyPoint> roboRoomHyphenTE = bob.toList();
			
			if (roboRoomHyphenTE.size() < 1)
			{
				System.out.println("Could not detect cube");
				continue;
			}

			int maxIndex = 0;
			float maxVal = -1;
			for (int i = 0; i < roboRoomHyphenTE.size();i++)
			{
				KeyPoint point = roboRoomHyphenTE.get(i);
				if (point.size > maxVal)
				{
					maxIndex = i;
					maxVal = point.size;
				}
			}
			
			KeyPoint majorKey = roboRoomHyphenTE.get(maxIndex);
			System.out.println("The x value of the box is : " + majorKey.pt.x);
			System.out.println("The y value of the box is : " + majorKey.pt.y);
		}
	}
}