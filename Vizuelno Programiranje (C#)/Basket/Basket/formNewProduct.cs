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
    public partial class formNewProduct : Form
    {
        public Product product;

        public formNewProduct()
        {
            InitializeComponent();
        }

        private void btnOk_Click(object sender, EventArgs e)
        {
            if(tbNameAdd.Text.Length>0 && tbCatAdd.Text.Length>0 && tbPriceAdd.Text.Length>0) {
                product = new Product(tbNameAdd.Text, tbCatAdd.Text, tbPriceAdd.Text);
                DialogResult= DialogResult.OK;
            }
            else
            {
                DialogResult= DialogResult.Cancel;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
        }

        private void tbNameAdd_Validating(object sender, CancelEventArgs e)
        {
            if(string.IsNullOrEmpty(tbNameAdd.Text))
            {
                e.Cancel = true;
                epName.SetError(tbNameAdd, "Specify name");
            }
            else
            {
                epName.SetError(tbNameAdd, null);
            }
        }
    }
}
