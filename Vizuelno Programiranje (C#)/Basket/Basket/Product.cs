using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Basket
{
    public class Product
    {
        private TextBox tbNameAdd;
        private TextBox tbCatAdd;
        private TextBox tbPriceAdd;

        public String name { get; set; }
        public String category { get; set; }
        public decimal price { get; set; }

        public Product(string name, string category, String price)
        {
            this.name = name;
            this.category = category;
            this.price = decimal.Parse(price);
        }

       

        public override String ToString()
        {
            return name;
        }
    }
}
