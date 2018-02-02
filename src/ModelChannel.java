public class ModelChannel {
private String language; //|LANGUAGE IS A STRING TYPE VARIABLE
private String name; //|NAME IS A STRING TYPE VARIABLE
private String streamURL; //|STREAMURL IS A STRING TYPE VARIABLE
public ModelChannel(String argLanguage, String argName, String argStreamURL) {
this.language =  argLanguage;
this.name =  argName;
this.streamURL =  argStreamURL;
}
 //|――――|GETLANGUAGE METHOD RETURNS STRING―――――――――――|
public String getLanguage() {
return this.language;
}
 //|――――|SETLANGUAGE METHOD TAKE STRING TYPE ARGUMENT
public void  setLanguage(String argLanguage) {
this.language = argLanguage;
}
 //|――――|GETNAME METHOD RETURNS STRING―――――――――――――――|
public String getName() {
return this.name;
}
 //|――――|SETNAME METHOD TAKE STRING TYPE ARGUMENT――――|
public void  setName(String argName) {
this.name = argName;
}
 //|――――|GETSTREAMURL METHOD RETURNS STRING――――――――――|
public String getStreamURL() {
return this.streamURL;
}
 //|――――|SETSTREAMURL METHOD TAKE STRING TYPE ARGUMENT
public void  setStreamURL(String argStreamURL) {
this.streamURL = argStreamURL;
}
public String toString() {
return "STRING_VALUE_OF_THE_MODELCHANNEL_MODEL: " + this.language + this.name + this.streamURL;
}
}