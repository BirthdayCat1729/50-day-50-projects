using System;
using System.Net;
using System.Net.Sockets;
using System.Windows.Forms;

namespace IPTool
{
    public partial class Form1 : Form
    {

        public Form1()
        {
            InitializeComponent();
        }

        private async void btnLookup_Click(object sender, EventArgs e)
        {
            try
            {
                string hostname = txtHostname.Text;
                IPHostEntry hostEntry = await Dns.GetHostEntryAsync(hostname);

                txtHostnameResult.Text = hostEntry.HostName;
                txtDomainResult.Text = hostEntry.HostName.Contains(".") ? hostEntry.HostName.Substring(hostEntry.HostName.IndexOf(".") + 1) : "";

                string ipv4Addresses = "";
                string ipv6Addresses = "";
                foreach (IPAddress ipAddress in hostEntry.AddressList)
                {
                    if (ipAddress.AddressFamily == AddressFamily.InterNetwork)
                    {
                        ipv4Addresses += ipAddress.ToString() + Environment.NewLine;
                    }
                    else if (ipAddress.AddressFamily == AddressFamily.InterNetworkV6)
                    {
                        ipv6Addresses += ipAddress.ToString() + Environment.NewLine;
                    }
                }
                txtIPv4Result.Text = ipv4Addresses.Trim();
                txtIPv6Result.Text = ipv6Addresses.Trim();

            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }

        private void txtIPAddress_TextChanged(object sender, EventArgs e)
        {

        }

        private void guna2TextBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void guna2Panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void guna2Panel2_Paint(object sender, PaintEventArgs e)
        {

        }

        private void guna2Button2_Click(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Minimized;
        }

        private void guna2Button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
