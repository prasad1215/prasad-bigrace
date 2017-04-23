

package prasad.chouti.thebigrace.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Date   : 3/29/17
 * Time   : 8:59 AM
 */

public class Contestants {

    @SerializedName("Name")
    private String Name;
    @SerializedName("Runners")
    private List<Runners> Runners;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Runners> getRunners() {
        return Runners;
    }

    public void setRunners(List<Runners> Runners) {
        this.Runners = Runners;
    }

    public static class Runners {
        @SerializedName("Name")
        private String Name;
        @SerializedName("Time")
        private int Time;
        @SerializedName("Age")
        private int Age;

        private int rank;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getTime() {
            return Time;
        }

        public void setTime(int Time) {
            this.Time = Time;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }
    }
}
