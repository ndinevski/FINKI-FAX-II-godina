using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Basket
{
    public partial class form : Form
    {
        public form()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(lbProducts.SelectedIndex != -1)
            {
                Product product = lbProducts.SelectedItem as Product;
                lbCart.Items.Add(product);
                updateCartTotal();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            formNewProduct form = new formNewProduct();

            if( form.ShowDialog() == DialogResult.OK )
            {
                lbProducts.Items.Add(form.product);
            }
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lbProducts.SelectedIndex != -1)
            {
                Product product = lbProducts.SelectedItem as Product;
                tbName.Text = product.name;
                tbCategory.Text = product.category;
                textBox3.Text = string.Format("{0:0.00}", product.price);
            }
            else
            {
                tbName.Clear();
                tbCategory.Clear();
                textBox3.Clear();
            }
        }

        private void btnDeleteProduct_Click(object sender, EventArgs e)
        {
            if(lbProducts.SelectedIndex != -1)
            {
                Product product = lbProducts.SelectedItem as Product;
                lbProducts.Items.Remove(product);
            }
        }

        private void lbCart_SelectedIndexChanged(object sender, EventArgs e)
        {
           
        }

        private void button6_Click(object sender, EventArgs e)
        {
            DialogResult dialog = MessageBox.Show("Are you sure you want to empty the list?", "Confirm", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (dialog == DialogResult.Yes)
            {
                lbProducts.Items.Clear();
                tbName.Clear();
                tbCategory.Clear();
                textBox3.Clear();
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            DialogResult dialog = MessageBox.Show("Are you sure you want to empty the list?", "Confirm", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (dialog == DialogResult.Yes)
            {
                lbCart.Items.Clear();
                updateCartTotal();
            }
        }

        public void updateCartTotal()
        {
            decimal totalPrice = 0;
            foreach(Product product in lbCart.Items) {
                totalPrice+= product.price;
            }
            textBox1.Text = string.Format("{0:0.00}", totalPrice);
        }

        private void btnRmBasket_Click(object sender, EventArgs e)
        {
            if(lbCart.SelectedIndex== -1)
            {
                Product product = lbCart.SelectedItem as Product;
                lbCart.Items.Remove(product);
                updateCartTotal();
            }
        }
    }
}
