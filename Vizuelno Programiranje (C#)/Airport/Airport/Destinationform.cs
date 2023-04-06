using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Airport
{
    public partial class fAddDest : Form
    {

        public Destination dest { get; set; }
        public fAddDest()
        {
            InitializeComponent();
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            if(tbNameDest.Text.Length > 0) { 
                dest = new Destination(tbNameDest.Text, nudLengthDest.Value, nudPriceDest.Value);
                DialogResult= DialogResult.OK;
            }
            else
            {
                DialogResult= DialogResult.Cancel;
            }
        }

        private void btnCancelDest_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
        }

        private void tbNameDest_Validating(object sender, CancelEventArgs e)
        {
            if(string.IsNullOrEmpty(tbNameDest.Text))
            {
                e.Cancel = true;
                errorProvider1.SetError(tbNameDest, "enter name");
            }
            else
            {
                errorProvider1.SetError(tbNameDest, null);
            }
        }
    }
}
