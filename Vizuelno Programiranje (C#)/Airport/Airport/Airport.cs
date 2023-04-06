using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;

namespace Airport
{
    public partial class fNewAirport : Form
    {
        public Airpot airport { get; set; }

        public fNewAirport()
        {
            InitializeComponent();
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            if(tbCityAirport.Text.Length>0 && tbLabelAirport.Text.Length==3 && tbNameAirport.Text.Length>0 ) {
                airport = new Airpot(tbCityAirport.Text, tbNameAirport.Text, tbLabelAirport.Text);
                this.DialogResult = DialogResult.OK;
            }
            else
            {
                this.DialogResult= DialogResult.Cancel;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void tbCityAirport_Validating(object sender, CancelEventArgs e)
        {
            if(string.IsNullOrEmpty(tbCityAirport.Text) )
            {
                e.Cancel = true;
                epCity.SetError(tbCityAirport, "Enter city name");
            }
            else
            {
                epCity.SetError(tbCityAirport, null);
            }
        }

        private void tbNameAirport_Validating(object sender, CancelEventArgs e)
        {
            if (string.IsNullOrEmpty(tbNameAirport.Text))
            {
                e.Cancel = true;
                epName.SetError(tbNameAirport, "Enter name");
            }
            else
            {
                epName.SetError(tbNameAirport, null);
            }
        }

        private void tbLabelAirport_Validating(object sender, CancelEventArgs e)
        {
            if(tbLabelAirport.Text.Length!=3)
            {
                e.Cancel = true;
                epLabel.SetError(tbLabelAirport, "Enter label");
            }
            else
            {
                epLabel.SetError(tbLabelAirport, null);
            }
        }
    }
}
