package foodmanagement;

import java.util.*;

public class FoodManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**************WELCOME*************** ");
        while (true) {
            int ch;
            System.out.println("Login as :");
            System.out.println("1.User");
            System.out.println("2.Hotel");
            System.out.println("3.Admin");
            System.out.println("4.Exit");
            System.out.print("Option : ");
         
            try {
                ch = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter integer as input");
                sc.next();
                continue;
            }
            switch (ch) {
                case 1: {

                    System.out.println("Enter your name : ");
                    String temp_name=sc.next();
                    User user=new User();
                    user=user.userDetails(temp_name);
                    user.user_flow(user);
                    break;
                }
                case 2: {
                    Hotel hotel = new Hotel();
                    System.out.println("Enter your hotel name : ");
                    String h_name = sc.next();
                    hotel=hotel.hotelDetails(h_name);
                    hotel.hotel_flow(hotel);
                    break;
                }
                case 3: {

                    adminloop:
                    while (true) {
                        int ch_admin =0;
                        System.out.println("1.Show user data ");
                        System.out.println("2.Show Hotels data");
                        System.out.println("3.Exit");
                        System.out.print("Enter your Option : ");
                        try {
                            ch_admin = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Enter integer as input");
                            sc.next();
                            continue;
                        }
                        switch (ch_admin) {
                            case 1: {
                                User u = new User();
                                u.printAllUser();
                                break;
                            }
                            case 2: {
                                if (Admin.hotel_list.isEmpty()) {
                                    System.out.println("Sorry no Hotels Available at the moment");
                                    break;
                                }
                                for (Hotel ht : Admin.hotel_list) {
                                    System.out.println(ht.hotel_name + " Menu...");
                                    System.out.println("food \t Amount Quantity");
                                    for (String fname : ht.menu_aval.keySet()) {
                                        System.out.println(fname + "\t" + ht.menu_cost.get(fname) + "\t" + ht.menu_aval.get(fname));
                                    }
                                    System.out.println("-----------------------");
                                }
                                break;
                            }
                            case 3:
                                break adminloop;
                            default: {
                                System.out.println("Invalid Input number");
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Thank you for the visit ..............");
                    System.exit(0);
                }
                default: {
                    System.out.println("Invalid Option try between 1 to 4 ");
                }

            }

        }

    }

}
