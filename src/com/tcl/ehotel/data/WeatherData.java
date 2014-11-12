package com.tcl.ehotel.data;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherData implements Parcelable{
	public int weatherId; 
	
	public String weather_lang1;
	public String current_lang1;
	public String minTemp_lang1;
	public String maxTemp_lang1;
	public String air_lang1;
	public String aqi_lang1;
	
	public String weather_lang2;
	public String current_lang2;
	public String minTemp_lang2;
	public String maxTemp_lang2;
	public String air_lang2;
	public String aqi_lang2;
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(weatherId);
		parcel.writeString(weather_lang1);
		parcel.writeString(current_lang1);
		parcel.writeString(minTemp_lang1);
		parcel.writeString(maxTemp_lang1);
		parcel.writeString(air_lang1);
		parcel.writeString(aqi_lang1);
		
		parcel.writeString(weather_lang2);
		parcel.writeString(current_lang2);
		parcel.writeString(minTemp_lang2);
		parcel.writeString(maxTemp_lang2);
		parcel.writeString(air_lang2);
		parcel.writeString(aqi_lang2);
	}
	public static final Parcelable.Creator<WeatherData> CREATOR =new Creator<WeatherData>(){

		@Override
		public WeatherData createFromParcel(Parcel parcel) {
			WeatherData data = new WeatherData();
			data.weatherId =parcel.readInt();
			data.weather_lang1 =parcel.readString();
			data.current_lang1 =parcel.readString();
			data.minTemp_lang1=parcel.readString();
			data.maxTemp_lang1=parcel.readString();
			data.air_lang1 =parcel.readString();
			data.aqi_lang1=parcel.readString();
			
			data.weather_lang2=parcel.readString();
			data.current_lang2=parcel.readString();
			data.minTemp_lang2=parcel.readString();
			data.maxTemp_lang2=parcel.readString();
			data.air_lang2=parcel.readString();
			data.aqi_lang2=parcel.readString();
			return data;
		}

		@Override
		public WeatherData[] newArray(int size) {
			return new WeatherData[size];
		}
	};
	public String toString() {
		StringBuilder builder =new StringBuilder();
		builder.append("weather:")
		.append("weatherId->")
		.append(weatherId)
		.append(" language1, weather->")
		.append(weather_lang1)
		.append(", current->")
		.append(current_lang1)
		.append(", min->")
		.append(minTemp_lang1)
		.append(", max->")
		.append(maxTemp_lang1)
		.append(", air")
		.append(air_lang1)
		.append(", aqi->")
		.append(aqi_lang1)
		.append(",----------- language2, weather->")
		.append(weather_lang2)
		.append(", current->")
		.append(current_lang2)
		.append(", min->")
		.append(minTemp_lang2)
		.append(", max->")
		.append(maxTemp_lang2)
		.append(", air")
		.append(air_lang2)
		.append(", aqi->")
		.append(aqi_lang2);
		return builder.toString();
	};
}
