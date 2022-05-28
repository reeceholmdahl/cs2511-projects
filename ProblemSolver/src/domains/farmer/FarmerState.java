/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.farmer;

import framework.problem.State;
import java.util.stream.Stream;

public class FarmerState extends State {
    
        public FarmerState(String farmer, String wolf, String goat, String cabbage) {
            this.farmer = farmer;
            this.wolf = wolf;
            this.goat = goat;
            this.cabbage = cabbage;
        }

        @Override
        public boolean equals(Object other) {
            FarmerState otherFarmer = (FarmerState) other;
            return otherFarmer != null && this.farmer.equals(otherFarmer.farmer) && this.wolf.equals(otherFarmer.wolf) && this.goat.equals(otherFarmer.goat) && this.cabbage.equals(otherFarmer.cabbage);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            final String BLANK  = "   |  |   ";
            final String farmer = this.farmer.equals("West")    ? " F |  |   \n" : "   |  | F \n";
            final String wolf = this.wolf.equals("West")        ? " W |  |   \n" : "   |  | W \n";
            final String goat = this.goat.equals("West")        ? " G |  |   \n" : "   |  | G \n";
            final String cabbage = this.cabbage.equals("West")  ? " C |  |   \n" : "   |  | C \n";
            
            sb.append(BLANK);
            sb.append(NEW_LINE);
            sb.append(farmer);
            sb.append(wolf);
            sb.append(goat);
            sb.append(cabbage);
            sb.append(BLANK);
            
            return sb.toString();
        }
        
        public String getFarmer() {
            return farmer;
        }
        
        public String getWolf() {
            return wolf;
        }
        
        public String getGoat() {
            return goat;
        }
        
        public String getCabbage() {
            return cabbage;
        }

        private final String farmer;
        private final String wolf;
        private final String goat;
        private final String cabbage;
        

        private static final String NEW_LINE = "\n";
}