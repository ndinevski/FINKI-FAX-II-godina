using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PizzaOrder
{
    public class Desert
    {
        public String name { get; set; }
        public int price { get; set; }

        public Desert(string name, int price)
        {
            this.name = name;
            this.price = price;
        }

        public override String ToString()
        {
            return name;
        }
    }
}
