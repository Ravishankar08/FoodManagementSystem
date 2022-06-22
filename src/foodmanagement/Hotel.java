package foodmanagement;

import java.util.*;

class Hotel extends Food {
    private String hotelpass;
    Scanner sc = new Scanner(System.in);
    int user_op;
    
    String hotel_name;
    
    
    Hotel hotelDetails(String h_name) {
        
        for (Hotel h : Admin.hotel_list) {
            if (h.hotel_name.equals(h_name)) {
                System.out.println("Relogging.....");
                return h;
            }
        }
        this.hotel_name = h_name;
        Admin.hotel_list.add(this);
        return this;
    }

    boolean update_avail(String food_update) {
        return this.menu_aval.containsKey(food_update);
    }

    void hotel_flow(Hotel h) {
        System.out.println("Hotels workflow");
        while (true) {
            System.out.println("1.Add new Food");
            System.out.println("2.Display today's menu");
            System.out.println("3.Delete Hotel");
            System.out.println("4.Update Availability");
            System.out.println("5.Exit");
            System.out.println("Enter your Option : ");
            try {
                user_op = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Provide Integer as input");
                sc.next();
                continue;
            }
            switch (user_op) {
                case 1: {
                    h.addTodaysMenu();
                    break;
                }
                case 2: {
                    System.out.println("******************Today's Menu********************* ");
                    h.display_menu();
                    break;
                }
                case 3: {
                    System.out.println("Do you want to delete "+this.hotel_name);
                    System.out.println("1.Yes\n2.No");
                    System.out.println("Enter your Option : ");
                    try{
                        if(sc.nextInt()==1){
                            Admin.hotel_list.remove(this);
                            System.out.println(this.hotel_name+" Deleted successfully...");
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Enter an integer value ..");
                    }
                    break;
                }
                case 4: {
                    int newQuan;
                    System.out.print("Enter the food name : ");
                    String name_food = sc.next();
                    if (h.update_avail(name_food)) {
                        while (true) {
                            try {
                                System.out.print("Enter the new quantity : ");
                                newQuan = sc.nextInt();
                                break;

                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input format");
                                sc.next();
                            }
                        }
                        h.menu_aval.replace(name_food, newQuan);
                        System.out.println("Updated Successfully");
                    } else {
                        System.out.println("Sorry no food match");
                    }
                    break;

                }
                case 5: {
                    return;
                }
                default: {
                    System.out.println("invalid Input");
                }
            }
        }
    }

    void display_menu() {
        if (this.menu_aval.isEmpty()) {
            System.out.println("Sorry no Data found");
        } else {
            System.out.println("Name \tCost\t Stock");
            for (String f_name : this.menu_aval.keySet()) {
                System.out.println("--------------------------");
                System.out.println(f_name + "\t" + this.menu_cost.get(f_name) + "\t" + this.menu_aval.get(f_name));

            }
            System.out.println("--------------------------");
        }
    }

    void addTodaysMenu() {
        int temp_ch,temp_food_cost,temp_food_avail;
        String temp_food_name;
        System.out.println("Enter " + this.hotel_name + "'s Menu.");
        while (true) {
            System.out.println("Enter the food name : ");
            temp_food_name = sc.next();
            if (menu_aval.containsKey(temp_food_name)) {
                System.out.println("Sorry Item Already exist");
            } else {
                while (true) {
                    try {
                        System.out.println("Enter the food cost : ");
                         temp_food_cost = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format");
                        sc.next();

                    }
                }
                
                while (true) {
                    try {
                        System.out.println("Enter the food availability : ");
                        temp_food_avail = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format");
                        sc.next();
                    }
                }
                this.menu_aval.put(temp_food_name, temp_food_avail);
                this.menu_cost.put(temp_food_name, temp_food_cost);
            }
            System.out.println("Do you want to add one more ....");
            System.out.println("1.Yes");
            System.out.println("2.No");
            while(true){
                try{
                    temp_ch = sc.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input fomat");
                    sc.next();
                }
            }
            if (temp_ch != 1) {
                System.out.println("Menu added successfully");
                break;
            }
        }
    }

}
