package foodmanagement;

import java.util.*;

class User {
    
    
    ArrayList<user_order_class> user_order = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private String user_name, user_address,userpass;
    private long user_number, total_amt;

    User userDetails(String temp_user_name) {
        for (User u : Admin.user_list) {
            if (u.user_name.equals(temp_user_name)) {
                System.out.println("Relogging...");
                return  u;
            }
        }
        
        this.user_name = temp_user_name;
        System.out.print("Enter Your Address : ");
        this.user_address = sc.next();
        System.out.print("Enter your contact Number  :");
        while (true) {
            try {
                this.user_number = sc.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer as input");
                sc.next();
            }
        }
        Admin.user_list.add(this);
        return this;
    }

    private void select_hotel() {
        int i = 1;

        for (Hotel h : Admin.hotel_list) {
            System.out.println(i + ". " + h.hotel_name);
            i++;
        }
        if (i == 1) {
            System.out.println("No Hotels Available at the moment");
            return;
        }
        System.out.print("Enter hotel Number : ");
        int hotel_ch;
        while (true) {
            try {
                hotel_ch = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input Format");
                sc.next();
            }
        }
        hotel_ch--;
        if (hotel_ch > Admin.hotel_list.size()) {
            System.out.println("Invalid Input");
            select_hotel();
        }
        Hotel curr_hotel = Admin.hotel_list.get(hotel_ch);
        System.out.println(curr_hotel.hotel_name + " Menu");
        hotel_menu(curr_hotel);

    }

    private void hotel_menu(Hotel curr_hotel) {
        if (curr_hotel.menu_aval.isEmpty()) {

            System.out.println("Menu Unavailable..");
            return;
        }

        System.out.println("Name \t Stock \tCost");
        for (String f_name : curr_hotel.menu_aval.keySet()) {
            System.out.println(f_name + "\t" + curr_hotel.menu_aval.get(f_name) + "\t" + curr_hotel.menu_cost.get(f_name));
        }
        place_order(curr_hotel);
    }

    private void place_order(Hotel curr_hotel) {
        while (true) {
            String temp_name;
            int temp_quan;
            System.out.print("Enter the food name : ");
            try {
                temp_name = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Enter integer as input");
                sc.next();
                continue;
            }

            if (!curr_hotel.menu_aval.containsKey(temp_name)) {
                System.out.println("Not a valid food Name....");
                this.hotel_menu(curr_hotel);
            }
            
              if (curr_hotel.menu_aval.get(temp_name) == 0) {
                System.out.println("No stocks available for " + temp_name);
                continue;

            }
           
            while(true){
                try {
                System.out.print("Enter the quantity : ");
                temp_quan = sc.nextInt();
                if(temp_quan<=0){
                    System.out.println("Atleast one item must be selected");
                }
                else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter integer as input");
                sc.next();
            }
            }
            

            if (curr_hotel.menu_aval.get(temp_name) < temp_quan) {
                int cur_avail = curr_hotel.menu_aval.get(temp_name);
                while (true) {
                    System.out.println("Only " + cur_avail + " Item available ");
                    System.out.println("Enter a quantity with atmost " + cur_avail);
                    try {
                        temp_quan = sc.nextInt();
                        if (!(curr_hotel.menu_aval.get(temp_name) < temp_quan)) {
                            break;
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Enter integer as input");
                        sc.next();
                    }
                }
            }
            user_order_class user_order_list;
            user_order_list = new user_order_class();
            user_order_list.Hotel = curr_hotel.hotel_name;
            user_order_list.food = temp_name;
            user_order_list.quantity = temp_quan;
            curr_hotel.menu_aval.replace(temp_name, curr_hotel.menu_aval.get(temp_name) - temp_quan);
            user_order_list.amount = curr_hotel.menu_cost.get(temp_name) * temp_quan;
            total_amt += user_order_list.amount;
            this.user_order.add(user_order_list);
            System.out.println("Do you want to add more  ..");
            System.out.println("1.Yes\n2.No");
            System.out.print("Enter your option : ");
            int user_op ;
            while(true){
                try{
                    user_op=sc.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Enter  an Integer value : ");
                    sc.next();
                }
            }
            if (user_op == 1) {
                select_hotel();
                return;
            } else {
                System.out.println("Item added successfully");
                return;
            }
        }
    }

    void display_order() {
        if (this.user_order.isEmpty()) {
            System.out.println("No Orders yet");
            return;
        }
        System.out.println("Name \t Quantity \t Cost \tHotel");
        for (user_order_class each_order : this.user_order) {
            System.out.println(each_order.food + "\t\t" + each_order.quantity + "\t" + each_order.amount + "\t" + each_order.Hotel);
        }
        System.out.println("Total : " + total_amt);
    }

    void user_flow(User u) {
        int ch ;
        while (true) {
            System.out.println("Welcome  " + u.user_name);
            System.out.println("1.Order food");
            System.out.println("2.Show My Order");
            System.out.println("3.Exit");
            System.out.print("Enter your Option : ");

            try {
                ch = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option format");
                sc.next();
                continue;

            }
            switch (ch) {

                case 1: {
                    u.select_hotel();
                    break;
                }
                case 2: {
                    u.display_order();
                    break;
                }
                case 3: {
                    return;
                }
                default: {
                    System.out.println("Sorry Invalid  Input");
                }

            }

        }
    }

    public void printAllUser() {
        if (Admin.user_list.isEmpty()) {
            System.out.println("No user Available at the moment");
            return;
        }
        for (User user : Admin.user_list) {
            System.out.println("Name : " + user.user_name + "\t Contact No : " + user.user_number);
            System.out.println("Address : " + user.user_address);
            System.out.println("Hotel \t Food \t Amount    Quantity");
            for (user_order_class order : user.user_order) {
                System.out.println(order.Hotel + "\t" + order.food + "\t   " + order.amount + "\t" + order.quantity);
            }
            System.out.println("\t\tTotal:" + user.total_amt);
            System.out.println("------------------------------------");

        }
    }
    

}
