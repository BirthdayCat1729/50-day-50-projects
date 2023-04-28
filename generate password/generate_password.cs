using System;
using System.Linq;

namespace generate_password
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Enter password length: ");
            int length = int.Parse(Console.ReadLine());
            string password = GeneratePassword(length);
            Console.WriteLine("Random Password: " + password);
        }

        static string GeneratePassword(int length)
        {
            string chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
            Random random = new Random();
            string password = new string(Enumerable.Repeat(chars, length)
                .Select(s => s[random.Next(s.Length)]).ToArray());

            return password;
        }
    }
}
