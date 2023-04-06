using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Airport
{
    public partial class fAirport : Form
    {
        public fAirport()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void btnAddAirport_Click(object sender, EventArgs e)
        {
            fNewAirport newForm = new fNewAirport();
            
            if(newForm.ShowDialog() == DialogResult.OK)
            {
                lbAirports.Items.Add(newForm.airport);
            }
        }

        private void btnRmAirport_Click(object sender, EventArgs e)
        {
            if (lbAirports.SelectedIndex != -1)
            {
                DialogResult confirmResult = MessageBox.Show("Are you sure you want to delete this airport?", "Confirmation", MessageBoxButtons.YesNo);
                if(confirmResult == DialogResult.Yes)
                {
                    lbAirports.Items.Remove(lbAirports.SelectedItem);
                }
            }
        }

        private void btnAddDest_Click(object sender, EventArgs e)
        {
            if(lbAirports.SelectedIndex!=-1)
            {

                fAddDest form = new fAddDest();
                if(form.ShowDialog() == DialogResult.OK)
                {
                    Airpot airport = lbAirports.SelectedItem as Airpot;
                    airport.addDestination(form.dest);
                    lbDestinations.Items.Add(form.dest);
                    tbMostExpensiveDest.Text = airport.mostExpensiveDest().ToString();
                    tbAverageLength.Text = string.Format("{0:0.##}", airport.averageDistance());
                }
            }
        }

        private void lbAirports_SelectedIndexChanged(object sender, EventArgs e)
        {
           lbDestinations.Items.Clear();

            if (lbAirports.SelectedIndex != -1)
            {
                Airpot airpot = lbAirports.SelectedItem as Airpot;

                foreach (Destination dest in airpot.destinations)
                {
                    lbDestinations.Items.Add(dest);
                }
                tbMostExpensiveDest.Text = airpot.mostExpensiveDest().ToString();
                tbAverageLength.Text = string.Format("{0:0.##}", airpot.averageDistance());
            }
        }
    }
}
