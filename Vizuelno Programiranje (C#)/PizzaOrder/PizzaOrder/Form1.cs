using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PizzaOrder
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            Desert desert1 = new Desert("Овошна пита", 50);
            Desert desert2 = new Desert("Сладолед", 60);
            Desert desert3 = new Desert("Торта", 100);
            lbDeserts.Items.Add(desert1);
            lbDeserts.Items.Add(desert2);
            lbDeserts.Items.Add(desert3);
            lbDeserts.SelectedItem = desert1;
            tbDesertPrice.Text = desert1.price.ToString();
            updatePrice();
        }

        private void rbMala_CheckedChanged(object sender, EventArgs e)
        {
            //rbSredna.Checked = false;
            //rbGolema.Checked = false;
            updatePrice();
        }

        private void rbSredna_CheckedChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void rbGolema_CheckedChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbKolKoka_TextChanged(object sender, EventArgs e)
        {
            tbTotalKoka.Text = (int.Parse(tbCenaKoka.Text) * int.Parse(tbKolKoka.Text)).ToString();
            updatePrice();
        }

        private void tbCenaKoka_TextChanged(object sender, EventArgs e)
        {
            tbTotalKoka.Text = (int.Parse(tbCenaKoka.Text) * int.Parse(tbKolKoka.Text)).ToString();
            updatePrice();
        }

        private void groupBox3_Enter(object sender, EventArgs e)
        {

        }

        private void tbKolSok_TextChanged(object sender, EventArgs e)
        {
            tbTotalSok.Text = (int.Parse(tbCenaSok.Text) * int.Parse(tbKolSok.Text)).ToString();
            updatePrice();
        }

        private void tbCenaSok_TextChanged(object sender, EventArgs e)
        {
            tbTotalSok.Text = (int.Parse(tbCenaSok.Text) * int.Parse(tbKolSok.Text)).ToString();
            updatePrice();
        }

        private void tbKolPivo_TextChanged(object sender, EventArgs e)
        {
            tbTotalPivo.Text = (int.Parse(tbCenaPivo.Text) * int.Parse(tbKolPivo.Text)).ToString();
            updatePrice();
        }

        private void tbCenaPivo_TextChanged(object sender, EventArgs e)
        {
            tbTotalPivo.Text = (int.Parse(tbCenaPivo.Text) * int.Parse(tbKolPivo.Text)).ToString();
            updatePrice();
        }

        private void lbDeserts_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lbDeserts.SelectedIndex!= -1)
            {
                Desert desert = lbDeserts.SelectedItem as Desert;
                tbDesertPrice.Text = desert.price.ToString();
                updatePrice();
            }
        }

        public void updatePrice()
        {
            int goleminaCena=0, dodatociCena=0, pijalociCena=0, desertCena=0;

            if (rbMala.Checked)
            {
                goleminaCena = 0;
                int.TryParse(tbMala.Text,out goleminaCena);
            }else if (rbSredna.Checked)
            {
                goleminaCena = 0;
                int.TryParse(tbSredna.Text, out goleminaCena);
            }
            else
            {
                goleminaCena = 0;
                int.TryParse(tbGolema.Text, out goleminaCena);
            }

            if(cbFefer.Checked)
            {
                int currentCena = 0;
                int.TryParse(tbFefer.Text, out currentCena);
                dodatociCena += currentCena;
            }
            if (cbSirenje.Checked)
            {
                int currentCena = 0;
                int.TryParse(tbSirenje.Text, out currentCena);
                dodatociCena += currentCena;
            }
            if (cbKecap.Checked)
            {
                int currentCena = 0;
                int.TryParse(tbKecap.Text, out currentCena);
                dodatociCena += currentCena;
            }

            int koka = 0;
            int pivo = 0;
            int sok = 0;
            int.TryParse(tbTotalKoka.Text, out koka);
            int.TryParse(tbTotalPivo.Text, out pivo);
            int.TryParse(tbTotalSok.Text, out sok);
            pijalociCena = koka + pivo + sok;

            desertCena = 0;
            int.TryParse(tbDesertPrice.Text, out desertCena);

            tbTotal.Text = (goleminaCena + dodatociCena + pijalociCena + desertCena).ToString();
        }

        private void tbFefer_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbSirenje_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbKecap_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void tbMala_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbSredna_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbGolema_TextChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void cbFefer_CheckedChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void cbSirenje_CheckedChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void cbKecap_CheckedChanged(object sender, EventArgs e)
        {
            updatePrice();
        }

        private void tbDesertPrice_TextChanged(object sender, EventArgs e)
        {
            if (lbDeserts.SelectedIndex != -1)
            {
                Desert desert = lbDeserts.SelectedItem as Desert;
                desert.price = int.Parse(tbDesertPrice.Text);
                updatePrice();
            }
        }

        private void btnOtkazi_Click(object sender, EventArgs e)
        {

            DialogResult dialog = MessageBox.Show("Dali ste sigurni deka sakate da ja otkazete narackata?","Potvrda",
                                                  MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if(dialog == DialogResult.Yes)
            {
                Application.Exit();
            }
            
        }

        private void btnNaracaj_Click(object sender, EventArgs e)
        {
            int naplateno = 0;
            int.TryParse(tbNaplateno.Text, out naplateno);

            int total = 0;
            int.TryParse(tbTotal.Text, out total);

            tbKusur.Text = (naplateno - total).ToString();
        }
    }
}
