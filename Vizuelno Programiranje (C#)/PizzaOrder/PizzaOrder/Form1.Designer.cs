namespace PizzaOrder
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.tbGolema = new System.Windows.Forms.TextBox();
            this.tbSredna = new System.Windows.Forms.TextBox();
            this.tbMala = new System.Windows.Forms.TextBox();
            this.rbGolema = new System.Windows.Forms.RadioButton();
            this.rbSredna = new System.Windows.Forms.RadioButton();
            this.rbMala = new System.Windows.Forms.RadioButton();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.tbKecap = new System.Windows.Forms.TextBox();
            this.cbKecap = new System.Windows.Forms.CheckBox();
            this.tbSirenje = new System.Windows.Forms.TextBox();
            this.cbSirenje = new System.Windows.Forms.CheckBox();
            this.tbFefer = new System.Windows.Forms.TextBox();
            this.cbFefer = new System.Windows.Forms.CheckBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.label6 = new System.Windows.Forms.Label();
            this.tbTotalPivo = new System.Windows.Forms.TextBox();
            this.tbTotalSok = new System.Windows.Forms.TextBox();
            this.tbTotalKoka = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.tbCenaPivo = new System.Windows.Forms.TextBox();
            this.tbCenaSok = new System.Windows.Forms.TextBox();
            this.tbCenaKoka = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.tbKolPivo = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.tbKolSok = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.tbKolKoka = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.btnOtkazi = new System.Windows.Forms.Button();
            this.btnNaracaj = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            this.tbDesertPrice = new System.Windows.Forms.TextBox();
            this.lbDeserts = new System.Windows.Forms.ListBox();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.label8 = new System.Windows.Forms.Label();
            this.tbKusur = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.tbTotal = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.tbNaplateno = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.tbGolema);
            this.groupBox1.Controls.Add(this.tbSredna);
            this.groupBox1.Controls.Add(this.tbMala);
            this.groupBox1.Controls.Add(this.rbGolema);
            this.groupBox1.Controls.Add(this.rbSredna);
            this.groupBox1.Controls.Add(this.rbMala);
            this.groupBox1.Location = new System.Drawing.Point(20, 20);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(274, 145);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Големина";
            this.groupBox1.Enter += new System.EventHandler(this.groupBox1_Enter);
            // 
            // tbGolema
            // 
            this.tbGolema.Location = new System.Drawing.Point(152, 110);
            this.tbGolema.Name = "tbGolema";
            this.tbGolema.Size = new System.Drawing.Size(100, 20);
            this.tbGolema.TabIndex = 5;
            this.tbGolema.Text = "500";
            this.tbGolema.TextChanged += new System.EventHandler(this.tbGolema_TextChanged);
            // 
            // tbSredna
            // 
            this.tbSredna.Location = new System.Drawing.Point(152, 77);
            this.tbSredna.Name = "tbSredna";
            this.tbSredna.Size = new System.Drawing.Size(100, 20);
            this.tbSredna.TabIndex = 4;
            this.tbSredna.Text = "300";
            this.tbSredna.TextChanged += new System.EventHandler(this.tbSredna_TextChanged);
            // 
            // tbMala
            // 
            this.tbMala.Location = new System.Drawing.Point(152, 45);
            this.tbMala.Name = "tbMala";
            this.tbMala.Size = new System.Drawing.Size(100, 20);
            this.tbMala.TabIndex = 3;
            this.tbMala.Text = "200";
            this.tbMala.TextChanged += new System.EventHandler(this.tbMala_TextChanged);
            // 
            // rbGolema
            // 
            this.rbGolema.AutoSize = true;
            this.rbGolema.Location = new System.Drawing.Point(21, 113);
            this.rbGolema.Name = "rbGolema";
            this.rbGolema.Size = new System.Drawing.Size(63, 17);
            this.rbGolema.TabIndex = 2;
            this.rbGolema.TabStop = true;
            this.rbGolema.Text = "Голема";
            this.rbGolema.UseVisualStyleBackColor = true;
            this.rbGolema.CheckedChanged += new System.EventHandler(this.rbGolema_CheckedChanged);
            // 
            // rbSredna
            // 
            this.rbSredna.AutoSize = true;
            this.rbSredna.Location = new System.Drawing.Point(21, 77);
            this.rbSredna.Name = "rbSredna";
            this.rbSredna.Size = new System.Drawing.Size(62, 17);
            this.rbSredna.TabIndex = 1;
            this.rbSredna.TabStop = true;
            this.rbSredna.Text = "Средна";
            this.rbSredna.UseVisualStyleBackColor = true;
            this.rbSredna.CheckedChanged += new System.EventHandler(this.rbSredna_CheckedChanged);
            // 
            // rbMala
            // 
            this.rbMala.AutoSize = true;
            this.rbMala.Location = new System.Drawing.Point(21, 45);
            this.rbMala.Name = "rbMala";
            this.rbMala.Size = new System.Drawing.Size(52, 17);
            this.rbMala.TabIndex = 0;
            this.rbMala.TabStop = true;
            this.rbMala.Text = "Мала";
            this.rbMala.UseVisualStyleBackColor = true;
            this.rbMala.CheckedChanged += new System.EventHandler(this.rbMala_CheckedChanged);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.tbKecap);
            this.groupBox2.Controls.Add(this.cbKecap);
            this.groupBox2.Controls.Add(this.tbSirenje);
            this.groupBox2.Controls.Add(this.cbSirenje);
            this.groupBox2.Controls.Add(this.tbFefer);
            this.groupBox2.Controls.Add(this.cbFefer);
            this.groupBox2.Location = new System.Drawing.Point(322, 20);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(274, 145);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Додатоци";
            // 
            // tbKecap
            // 
            this.tbKecap.Location = new System.Drawing.Point(153, 110);
            this.tbKecap.Name = "tbKecap";
            this.tbKecap.Size = new System.Drawing.Size(100, 20);
            this.tbKecap.TabIndex = 8;
            this.tbKecap.Text = "20";
            this.tbKecap.TextChanged += new System.EventHandler(this.tbKecap_TextChanged);
            // 
            // cbKecap
            // 
            this.cbKecap.AutoSize = true;
            this.cbKecap.Location = new System.Drawing.Point(26, 114);
            this.cbKecap.Name = "cbKecap";
            this.cbKecap.Size = new System.Drawing.Size(56, 17);
            this.cbKecap.TabIndex = 2;
            this.cbKecap.Text = "Кечап";
            this.cbKecap.UseVisualStyleBackColor = true;
            this.cbKecap.CheckedChanged += new System.EventHandler(this.cbKecap_CheckedChanged);
            // 
            // tbSirenje
            // 
            this.tbSirenje.Location = new System.Drawing.Point(153, 77);
            this.tbSirenje.Name = "tbSirenje";
            this.tbSirenje.Size = new System.Drawing.Size(100, 20);
            this.tbSirenje.TabIndex = 7;
            this.tbSirenje.Text = "30";
            this.tbSirenje.TextChanged += new System.EventHandler(this.tbSirenje_TextChanged);
            // 
            // cbSirenje
            // 
            this.cbSirenje.AutoSize = true;
            this.cbSirenje.Location = new System.Drawing.Point(26, 78);
            this.cbSirenje.Name = "cbSirenje";
            this.cbSirenje.Size = new System.Drawing.Size(105, 17);
            this.cbSirenje.TabIndex = 1;
            this.cbSirenje.Text = "Екстра сирење";
            this.cbSirenje.UseVisualStyleBackColor = true;
            this.cbSirenje.CheckedChanged += new System.EventHandler(this.cbSirenje_CheckedChanged);
            // 
            // tbFefer
            // 
            this.tbFefer.Location = new System.Drawing.Point(153, 45);
            this.tbFefer.Name = "tbFefer";
            this.tbFefer.Size = new System.Drawing.Size(100, 20);
            this.tbFefer.TabIndex = 6;
            this.tbFefer.Text = "40";
            this.tbFefer.TextChanged += new System.EventHandler(this.tbFefer_TextChanged);
            // 
            // cbFefer
            // 
            this.cbFefer.AutoSize = true;
            this.cbFefer.Location = new System.Drawing.Point(26, 45);
            this.cbFefer.Name = "cbFefer";
            this.cbFefer.Size = new System.Drawing.Size(87, 17);
            this.cbFefer.TabIndex = 0;
            this.cbFefer.Text = "Феферонки";
            this.cbFefer.UseVisualStyleBackColor = true;
            this.cbFefer.CheckedChanged += new System.EventHandler(this.cbFefer_CheckedChanged);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label6);
            this.groupBox3.Controls.Add(this.tbTotalPivo);
            this.groupBox3.Controls.Add(this.tbTotalSok);
            this.groupBox3.Controls.Add(this.tbTotalKoka);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.tbCenaPivo);
            this.groupBox3.Controls.Add(this.tbCenaSok);
            this.groupBox3.Controls.Add(this.tbCenaKoka);
            this.groupBox3.Controls.Add(this.label4);
            this.groupBox3.Controls.Add(this.tbKolPivo);
            this.groupBox3.Controls.Add(this.label3);
            this.groupBox3.Controls.Add(this.tbKolSok);
            this.groupBox3.Controls.Add(this.label2);
            this.groupBox3.Controls.Add(this.tbKolKoka);
            this.groupBox3.Controls.Add(this.label1);
            this.groupBox3.Location = new System.Drawing.Point(20, 193);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(576, 145);
            this.groupBox3.TabIndex = 2;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Пијалок";
            this.groupBox3.Enter += new System.EventHandler(this.groupBox3_Enter);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(482, 16);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(43, 13);
            this.label6.TabIndex = 17;
            this.label6.Text = "Вкупно";
            // 
            // tbTotalPivo
            // 
            this.tbTotalPivo.Location = new System.Drawing.Point(455, 102);
            this.tbTotalPivo.Name = "tbTotalPivo";
            this.tbTotalPivo.ReadOnly = true;
            this.tbTotalPivo.Size = new System.Drawing.Size(100, 20);
            this.tbTotalPivo.TabIndex = 16;
            this.tbTotalPivo.Text = "0";
            // 
            // tbTotalSok
            // 
            this.tbTotalSok.Location = new System.Drawing.Point(455, 69);
            this.tbTotalSok.Name = "tbTotalSok";
            this.tbTotalSok.ReadOnly = true;
            this.tbTotalSok.Size = new System.Drawing.Size(100, 20);
            this.tbTotalSok.TabIndex = 15;
            this.tbTotalSok.Text = "0";
            // 
            // tbTotalKoka
            // 
            this.tbTotalKoka.Location = new System.Drawing.Point(455, 37);
            this.tbTotalKoka.Name = "tbTotalKoka";
            this.tbTotalKoka.ReadOnly = true;
            this.tbTotalKoka.Size = new System.Drawing.Size(100, 20);
            this.tbTotalKoka.TabIndex = 14;
            this.tbTotalKoka.Text = "0";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(336, 16);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(33, 13);
            this.label5.TabIndex = 13;
            this.label5.Text = "Цена";
            // 
            // tbCenaPivo
            // 
            this.tbCenaPivo.Location = new System.Drawing.Point(302, 102);
            this.tbCenaPivo.Name = "tbCenaPivo";
            this.tbCenaPivo.Size = new System.Drawing.Size(100, 20);
            this.tbCenaPivo.TabIndex = 12;
            this.tbCenaPivo.Text = "80";
            this.tbCenaPivo.TextChanged += new System.EventHandler(this.tbCenaPivo_TextChanged);
            // 
            // tbCenaSok
            // 
            this.tbCenaSok.Location = new System.Drawing.Point(302, 69);
            this.tbCenaSok.Name = "tbCenaSok";
            this.tbCenaSok.Size = new System.Drawing.Size(100, 20);
            this.tbCenaSok.TabIndex = 11;
            this.tbCenaSok.Text = "70";
            this.tbCenaSok.TextChanged += new System.EventHandler(this.tbCenaSok_TextChanged);
            // 
            // tbCenaKoka
            // 
            this.tbCenaKoka.Location = new System.Drawing.Point(302, 37);
            this.tbCenaKoka.Name = "tbCenaKoka";
            this.tbCenaKoka.Size = new System.Drawing.Size(100, 20);
            this.tbCenaKoka.TabIndex = 10;
            this.tbCenaKoka.Text = "60";
            this.tbCenaKoka.TextChanged += new System.EventHandler(this.tbCenaKoka_TextChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(176, 16);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(55, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "Количина";
            // 
            // tbKolPivo
            // 
            this.tbKolPivo.Location = new System.Drawing.Point(152, 102);
            this.tbKolPivo.Name = "tbKolPivo";
            this.tbKolPivo.Size = new System.Drawing.Size(100, 20);
            this.tbKolPivo.TabIndex = 8;
            this.tbKolPivo.Text = "0";
            this.tbKolPivo.TextChanged += new System.EventHandler(this.tbKolPivo_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 105);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(33, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Пиво";
            // 
            // tbKolSok
            // 
            this.tbKolSok.Location = new System.Drawing.Point(152, 69);
            this.tbKolSok.Name = "tbKolSok";
            this.tbKolSok.Size = new System.Drawing.Size(100, 20);
            this.tbKolSok.TabIndex = 7;
            this.tbKolSok.Text = "0";
            this.tbKolSok.TextChanged += new System.EventHandler(this.tbKolSok_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(6, 72);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(140, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Сок од јаболко / портокал";
            // 
            // tbKolKoka
            // 
            this.tbKolKoka.Location = new System.Drawing.Point(152, 37);
            this.tbKolKoka.Name = "tbKolKoka";
            this.tbKolKoka.Size = new System.Drawing.Size(100, 20);
            this.tbKolKoka.TabIndex = 6;
            this.tbKolKoka.Text = "0";
            this.tbKolKoka.TextChanged += new System.EventHandler(this.tbKolKoka_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 40);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(143, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Кока кола / фанта / спрајт";
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.btnOtkazi);
            this.groupBox4.Controls.Add(this.btnNaracaj);
            this.groupBox4.Controls.Add(this.label7);
            this.groupBox4.Controls.Add(this.tbDesertPrice);
            this.groupBox4.Controls.Add(this.lbDeserts);
            this.groupBox4.Location = new System.Drawing.Point(20, 367);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(274, 145);
            this.groupBox4.TabIndex = 1;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "Десерт";
            // 
            // btnOtkazi
            // 
            this.btnOtkazi.Location = new System.Drawing.Point(152, 116);
            this.btnOtkazi.Name = "btnOtkazi";
            this.btnOtkazi.Size = new System.Drawing.Size(100, 23);
            this.btnOtkazi.TabIndex = 20;
            this.btnOtkazi.Text = "Откажи";
            this.btnOtkazi.UseVisualStyleBackColor = true;
            this.btnOtkazi.Click += new System.EventHandler(this.btnOtkazi_Click);
            // 
            // btnNaracaj
            // 
            this.btnNaracaj.Location = new System.Drawing.Point(152, 80);
            this.btnNaracaj.Name = "btnNaracaj";
            this.btnNaracaj.Size = new System.Drawing.Size(100, 23);
            this.btnNaracaj.TabIndex = 19;
            this.btnNaracaj.Text = "Нарачај";
            this.btnNaracaj.UseVisualStyleBackColor = true;
            this.btnNaracaj.Click += new System.EventHandler(this.btnNaracaj_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(149, 29);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(89, 13);
            this.label7.TabIndex = 18;
            this.label7.Text = "Цена на десерт:";
            // 
            // tbDesertPrice
            // 
            this.tbDesertPrice.Location = new System.Drawing.Point(152, 45);
            this.tbDesertPrice.Name = "tbDesertPrice";
            this.tbDesertPrice.Size = new System.Drawing.Size(100, 20);
            this.tbDesertPrice.TabIndex = 18;
            this.tbDesertPrice.TextChanged += new System.EventHandler(this.tbDesertPrice_TextChanged);
            // 
            // lbDeserts
            // 
            this.lbDeserts.FormattingEnabled = true;
            this.lbDeserts.Location = new System.Drawing.Point(9, 18);
            this.lbDeserts.Name = "lbDeserts";
            this.lbDeserts.Size = new System.Drawing.Size(120, 121);
            this.lbDeserts.TabIndex = 0;
            this.lbDeserts.SelectedIndexChanged += new System.EventHandler(this.lbDeserts_SelectedIndexChanged);
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.label8);
            this.groupBox5.Controls.Add(this.tbKusur);
            this.groupBox5.Controls.Add(this.label9);
            this.groupBox5.Controls.Add(this.tbTotal);
            this.groupBox5.Controls.Add(this.label10);
            this.groupBox5.Controls.Add(this.tbNaplateno);
            this.groupBox5.Location = new System.Drawing.Point(322, 367);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(274, 145);
            this.groupBox5.TabIndex = 3;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "Нарачка";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(72, 113);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(75, 13);
            this.label8.TabIndex = 20;
            this.label8.Text = "За вракање :";
            // 
            // tbKusur
            // 
            this.tbKusur.Location = new System.Drawing.Point(153, 110);
            this.tbKusur.Name = "tbKusur";
            this.tbKusur.ReadOnly = true;
            this.tbKusur.Size = new System.Drawing.Size(100, 20);
            this.tbKusur.TabIndex = 20;
            this.tbKusur.Text = "0";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(79, 72);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(68, 13);
            this.label9.TabIndex = 19;
            this.label9.Text = "Наплатено :";
            // 
            // tbTotal
            // 
            this.tbTotal.Location = new System.Drawing.Point(153, 29);
            this.tbTotal.Name = "tbTotal";
            this.tbTotal.ReadOnly = true;
            this.tbTotal.Size = new System.Drawing.Size(100, 20);
            this.tbTotal.TabIndex = 18;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(34, 32);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(113, 13);
            this.label10.TabIndex = 18;
            this.label10.Text = "Вкупно за плакање :";
            // 
            // tbNaplateno
            // 
            this.tbNaplateno.Location = new System.Drawing.Point(153, 69);
            this.tbNaplateno.Name = "tbNaplateno";
            this.tbNaplateno.Size = new System.Drawing.Size(100, 20);
            this.tbNaplateno.TabIndex = 19;
            this.tbNaplateno.Text = "0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(619, 545);
            this.Controls.Add(this.groupBox5);
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Нарачка на пица";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox4.PerformLayout();
            this.groupBox5.ResumeLayout(false);
            this.groupBox5.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox tbGolema;
        private System.Windows.Forms.TextBox tbSredna;
        private System.Windows.Forms.TextBox tbMala;
        private System.Windows.Forms.RadioButton rbGolema;
        private System.Windows.Forms.RadioButton rbSredna;
        private System.Windows.Forms.RadioButton rbMala;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox tbKecap;
        private System.Windows.Forms.CheckBox cbKecap;
        private System.Windows.Forms.TextBox tbSirenje;
        private System.Windows.Forms.CheckBox cbSirenje;
        private System.Windows.Forms.TextBox tbFefer;
        private System.Windows.Forms.CheckBox cbFefer;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox tbTotalPivo;
        private System.Windows.Forms.TextBox tbTotalSok;
        private System.Windows.Forms.TextBox tbTotalKoka;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox tbCenaPivo;
        private System.Windows.Forms.TextBox tbCenaSok;
        private System.Windows.Forms.TextBox tbCenaKoka;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox tbKolPivo;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbKolSok;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbKolKoka;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.Button btnOtkazi;
        private System.Windows.Forms.Button btnNaracaj;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox tbDesertPrice;
        private System.Windows.Forms.ListBox lbDeserts;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox tbKusur;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox tbTotal;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox tbNaplateno;
    }
}

