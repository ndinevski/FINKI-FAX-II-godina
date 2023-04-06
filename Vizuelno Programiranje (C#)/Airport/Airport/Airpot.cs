using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Airport
{
    public class Airpot
    {
        public String city { get; set; }
        public String name { get; set; }
        public String label { get; set; }

        public List<Destination> destinations { get; set; }

        public Airpot(string city, string name, string label)
        {
            this.city = city;
            this.name = name;
            this.label = label;
            this.destinations = new List<Destination>();
        }

        public decimal averageDistance()
        {
            decimal aDistance = 0;
            foreach(Destination dest in destinations)
            {
                aDistance += dest.distance;
            }
            if(destinations.Count>0) { 
                return aDistance / destinations.Count;
            }
            else
            {
                return 0;
            }
        }

        public String mostExpensiveDest()
        {
            if (destinations.Count > 0)
            {
                decimal price = 0;
                Destination maxDest = destinations[0];
                foreach (Destination dest in destinations)
                {
                    if (dest.price > 0)
                    {
                        price = dest.price;
                        maxDest = dest;
                    }
                }
                return maxDest.ToString();
            }
            else
            {
                return "";
            }
        }

        public void addDestination(Destination dest)
        {
            destinations.Add(dest);
        }

        public override string ToString()
        {
            return $"{label} - {name} - {city} ";
        }

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}
