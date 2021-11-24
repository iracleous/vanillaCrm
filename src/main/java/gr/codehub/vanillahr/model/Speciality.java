package gr.codehub.vanillahr.model;

public enum Speciality {

    DEVELOPER, MANAGER, QA, TESTER, ADMINISTRATOR, OTHER;


    public static Speciality getSpeciality(int value)
    {
        Speciality[] values = Speciality.values();
        if (value<values.length)
         return values[value];
        return null;
    }

}
