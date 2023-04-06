using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Airport
{
    public class Destination
    {
        public String name { get; set; }
        public decimal distance { get; set; }

        public decimal price { get; set; }

        public Destination(string name, decimal distance, decimal price)
        {
            this.name = name;
            this.distance = distance;
            this.price = price;
        }

        public override string ToString()
        {
            return $"{name} - {distance} km - {price} EUR";
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
