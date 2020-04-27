package com.example.contactapp;

import java.util.Random;

public class Randomizer {

    //rewrote the randomizer to fit the new basecontact set up

    private String[] firstNameList = {"James", "John", "Robert", "Micheal", "William",
            "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth"}; //Most common first names male and female

    private String[] lastNameList = {"Smith", "Johnson", "Williams", " Jones", "Brown",
            "Davis", "Miller", "Wilson", "Moore", "Taylor"};	//Most common last names

    private String[] middleNameList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private String[] nickNameList = {"Big Guy","The Dude","Cool Cat","Circle J","That one guy","That one girl","Whats his name","Whats her name",
                                    "some dude","some girl","some guy","nickname"};

    private String[] businessNamesList = {"Boba Boys", "Shirty Shirt", "Short Pants", "Long Shorts",
            "Slippah4Dayz", "LostLetters", "Simple Bizz"};

    private String[] streetList = {"Park", "Main", "Oak", "Pine", "Maple", "Cedar",
            "View", "Washington", "Lake", "Hill", "Mill"};

    private String[] streetOrientationList = {"N", "S", "E", "W"};

    private String[] streetTypeList = {"Dr","Ave"};

    private String[] cityList = {"Kingston", "Oakland", "Waverly", "Burlington", "Dayton", "Phoenix",
            "Las Vegas", "Los Angeles", "New York City", "Riverside", "Surprise"};

    private String[] stateList = {"Alabama", "Alaska", "Arizona", "Arkansas","California","Colorado",
            "Connecticut", "Delaware", "Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa",
            "Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota",
            "Mississippi","Missouri","Montana","Nebraska","Nevada","New Hamsphire","New Jersey","New Mexico",
            "New York","North Carolina", "North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
            "South Carolina", " North Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington",
            "West Virginia","Wisconsin","Wyoming"};

    private String[] shortStateList = {"AL", "AK", "AZ", "AR","CA","CO","CT","DE","FL","GA","HI",
            "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM"
            ,"NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

    //URL
    private String[] busURLList = {"Potato", "FrenchFries", "Oops", "GetMoreRam", "NotAScam",
            "TotallyAScam","Yikes","Oof","Poke", };

    //Personal Random
    private String[] monthList = {"January", "February", "March","April","May","June"
            ,"July", "August", "September", "October", "November", "December"};

    private String[] predescriptionList = {"Friendly", "Social", "Assertive", "Energetic", "Affectionate",
            "Caring", "Kind", "Weird", "Silly"};

    private String[] sufdescriptionList = {"Introvert", "Extrovert", "Ambivert", };

    private Random random = new Random();

    public BaseContact_Name makeName(){
        String firstname = firstNameList[random.nextInt(firstNameList.length)];
        String lastname = lastNameList[random.nextInt(lastNameList.length)];
        String middlename = middleNameList[random.nextInt(middleNameList.length)];
        String nickname = nickNameList[random.nextInt(nickNameList.length)];
        BaseContact_Name name = new BaseContact_Name(firstname,lastname);
        name.setMiddleName(middlename);
        name.setNickName(nickname);
        return name;
    }

    public String makePhone(){
        String phoneNumber = "";
        int num;
        num = random.nextInt(899)+100;
        phoneNumber = "("+num+")";
        num = random.nextInt(899)+100;
        phoneNumber+=num+"-";
        num = random.nextInt(8999)+1000;
        phoneNumber+=num;
        return  phoneNumber;
    }

    public BaseContact_Address makeAddress(){
                        // should show up as "9999 W Street Dr"
        String street = (random.nextInt(9998)+1) + " " + streetOrientationList[random.nextInt(streetOrientationList.length)]
                + " " +streetList[random.nextInt(streetList.length)]+ " "+streetTypeList[random.nextInt(streetTypeList.length)];
        String postal = Integer.toString(random.nextInt(89999)+10000);
        BaseContact_Address address = new BaseContact_Address(street,"",cityList[random.nextInt(cityList.length)],stateList[random.nextInt(stateList.length)],postal);
        address.setCountry("United States of America");
        return address;
    }

    public BaseContact_DateOfBirth makeDoB(){
        BaseContact_DateOfBirth birthday = new BaseContact_DateOfBirth(Integer.toString(random.nextInt(28)),
                monthList[random.nextInt(monthList.length)],
                Integer.toString(random.nextInt((2020-1900)+1)+1900));
        return birthday;
    }

    public String makeEmail(BaseContact person){
        //super generic email creation
        // Example: firstname_lastname Year born@gmail.com
        return (person.getName().getFirstName()+"_"+person.getName().getLastName()+person.getDateOfBirth().getYear()+"@gmail.com");
    }

    public String makeURL(){
        return ("https://www."+busURLList[random.nextInt(busURLList.length)]+".com");
    }

    public String makeDescription(){
        return (predescriptionList[random.nextInt(predescriptionList.length)]+ " "+ sufdescriptionList[random.nextInt(sufdescriptionList.length)]);
    }

    public BaseContact makeBaseContact(){
        BaseContact person = new BaseContact(makeName(),makePhone());
        person.setAddress(makeAddress());
        person.setDateOfBirth(makeDoB());
        person.setUrl(makeURL());
        person.setDescription(makeDescription());
        person.setEmail(makeEmail(person));
        return  person;
    }

}
