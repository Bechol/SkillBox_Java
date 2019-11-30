package homework_45.EnglishReader;

import java.util.Arrays;

/**
 * 4.5.2
 */
public class Main {

    private static final String text = "A means should also be available for tabulating the failure information " +
            "for determining trends and the mean-time-between-failure of the equipment. Besides being useful " +
            "to design and reliability engineering, this information may also be useful to maintenance personnel for " +
            "determining maintenance periodicity of the equipment already in use. Failures that require stress " +
            "statements shall include test failures, operator induced failures, test equipment failures, " +
            "environmental equipment failures, or any other problem which did result in overstress, " +
            "or which could have resulted in overstress, to any portion of flight hardware." +
            "The KSC PRACA System provides the means to monitor and track the health of the Space " +
            "Shuttle Ground Support Equipment and identify problem areas that require further investigation. " +
            "See figure 2 for the report that is used to identify and track problems at KSC. The majority of " +
            "problems that are reported deal with component failures. However, any other condition that " +
            "results in the system or equipment not meeting the requirements that were specified is also " +
            "reported (corrosion, out-of-calibration, etc.).";

    public static void main(String[] args) {
        Arrays.asList(text.split(" ")).forEach(System.out::println);
    }
}
