using System.Collections.Generic;
using Newtonsoft.Json;
using System.Net.Http;
using System.Windows;
using System.Net.Http.Json;
using System.Linq;
using System.Net;
using System.Text;

namespace Pos_Projekt
{
    public partial class MainWindow : Window
    {
        public HttpClient client = new HttpClient();

        public MainWindow()
        {
            InitializeComponent();
        }

        private async void GetByID(object sender, RoutedEventArgs e)
        {
            if (EnterId != null)
            {
                var response = await client.GetAsync($"http://localhost:8888/team/{EnterId.Text}");

                if (response.IsSuccessStatusCode)
                {
                    var json = await response.Content.ReadAsStringAsync();

                    if (json != null)
                    {
                        var team = JsonConvert.DeserializeObject<Teams>(json);
                        MyListView.ItemsSource = new List<Teams> { team };
                        MyListView.Visibility = Visibility.Visible;
                    }
                }
            }
        }

        private async void GetTeams(object sender, RoutedEventArgs e)
        {
        var repsons = await client.GetAsync("http://localhost:8888/teams");
        if (repsons.IsSuccessStatusCode)
        {
            var json = await repsons.Content.ReadAsStringAsync();
            if(json != null)
            {
                    var teams = JsonConvert.DeserializeObject<List<Teams>>(json);
                    MyListView.ItemsSource = teams;
                    MyListView.Visibility = Visibility.Visible;
                }

            }
        }

        private async void AddTeam(object sender, RoutedEventArgs e)
        {
            Teams newTeam = new Teams()
            {
                name = MyName.Text,
                location = MyLocation.Text,
                region = MyRegion.Text,
                founders = MyFounders.Text.Split(',').Select(s => s.Trim()).ToArray(),
                players = MyPlayers.Text.Split(',').Select(s => s.Trim()).ToArray(),
                url = MyUrl.Text
            };

            var client = new HttpClient();
            var response = await client.PostAsJsonAsync("http://localhost:8888/add/team", newTeam);

            if (response.IsSuccessStatusCode)
            {
                MessageBox.Show("Team has been added to the database.");
            }
            else
            {
                MessageBox.Show("Failed to add team to the database.");
            }

            MyName.Text = "";
            MyLocation.Text = "";
            MyRegion.Text = "";
            MyFounders.Text = "";
            MyPlayers.Text = "";
            MyUrl.Text = "";
        }

        private async void UpdateTeam(object sender, RoutedEventArgs e)
        {
            string id = IdU.Text;
            string name = MyNameU.Text;
            string location = MyLocationU.Text;
            string region = MyRegionU.Text;
            string[] founders = MyFoundersU.Text.Split(',');
            string[] players = MyPlayersU.Text.Split(',');
            string url = MyUrlU.Text;

            HttpClient client = new HttpClient();

            Teams updatedTeam = new Teams(id,name, location, region, founders, players, url);

            string json = JsonConvert.SerializeObject(updatedTeam);

            StringContent content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PutAsync($"http://localhost:8888/team/{id}", content);

            if (response.IsSuccessStatusCode)
            {
                MessageBox.Show("Team has been updated.");
            }
            else if (response.StatusCode == HttpStatusCode.NotFound)
            {
                MessageBox.Show("Team not found.");
            }
            else
            {
                MessageBox.Show("An error occurred while updating the team.");
            }
        }

        private async void DeleteTeam(object sender, RoutedEventArgs e)
        {
            var client = new HttpClient();
            var url = $"http://localhost:8888/del/team/{DeleteTeamId.Text}";

            var response = await client.DeleteAsync(url);

            if (response.IsSuccessStatusCode)
            {
                MessageBox.Show("Team has been deleted.");
            }
            else
            {
                MessageBox.Show("Failed to delete team.");
            }
        }
    }
}