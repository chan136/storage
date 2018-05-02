package com.example.storage.Utils;

import android.widget.ArrayAdapter;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.NewBuyer;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewCheckout;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.Checklist.Checklist;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jerry on 2017/12/15.
 */
public class SortListview {

    public static void deviceSort(ArrayAdapter<NewDevice> adapter, List<NewDevice> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return obj1.getName().compareTo(obj2.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return Integer.parseInt(obj1.getSKU())-Integer.parseInt(obj2.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return (int)obj1.getDate().getTime()-(int)obj2.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return obj2.getName().compareTo(obj1.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return Integer.parseInt(obj2.getSKU())-Integer.parseInt(obj1.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewDevice>() {
                        @Override
                        public int compare(NewDevice obj1, NewDevice obj2) {
                            return (int)obj2.getDate().getTime()-(int)obj1.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    public static void supplierSort(ArrayAdapter<NewSupplier> adapter, List<NewSupplier> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return obj1.getName().compareTo(obj2.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return Integer.parseInt(obj1.getSKU())-Integer.parseInt(obj2.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return (int)obj1.getDate().getTime()-(int)obj2.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return obj2.getName().compareTo(obj1.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return Integer.parseInt(obj2.getSKU())-Integer.parseInt(obj1.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewSupplier>() {
                        @Override
                        public int compare(NewSupplier obj1, NewSupplier obj2) {
                            return (int)obj2.getDate().getTime()-(int)obj1.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    }

    public static void buyerSort(ArrayAdapter<NewBuyer> adapter, List<NewBuyer> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return obj1.getName().compareTo(obj2.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return Integer.parseInt(obj1.getSKU())-Integer.parseInt(obj2.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return (int)obj1.getDate().getTime()-(int)obj2.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return obj2.getName().compareTo(obj1.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return Integer.parseInt(obj2.getSKU())-Integer.parseInt(obj1.getSKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewBuyer>() {
                        @Override
                        public int compare(NewBuyer obj1, NewBuyer obj2) {
                            return (int)obj2.getDate().getTime()-(int)obj1.getDate().getTime();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    }

    public static void checklistSort(ArrayAdapter<NewCheckin> adapter, List<NewCheckin> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getDevice_name().compareTo(obj2.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj1.getDevice_SKU())-Integer.parseInt(obj2.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getDate().compareTo(obj2.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getDevice_name().compareTo(obj1.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj2.getDevice_SKU())-Integer.parseInt(obj1.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getDate().compareTo(obj1.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    }


    public static void checkinSort(ArrayAdapter<NewCheckin> adapter, List<NewCheckin> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getDevice_name().compareTo(obj2.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj1.getDevice_SKU())-Integer.parseInt(obj2.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj1.getCount())-Integer.parseInt(obj2.getCount());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getDate().compareTo(obj2.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 5:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj1.getPrice())-Integer.parseInt(obj2.getPrice());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 6:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getSupplier_SKU().compareTo(obj2.getSupplier_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 7:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj1.getSupplier_name().compareTo(obj2.getSupplier_name());                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getDevice_name().compareTo(obj1.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj2.getDevice_SKU())-Integer.parseInt(obj1.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj2.getCount())-Integer.parseInt(obj1.getCount());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getDate().compareTo(obj1.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 5:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return Integer.parseInt(obj2.getPrice())-Integer.parseInt(obj1.getPrice());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 6:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getSupplier_SKU().compareTo(obj1.getSupplier_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 7:
                    Collections.sort(list, new Comparator<NewCheckin>() {
                        @Override
                        public int compare(NewCheckin obj1, NewCheckin obj2) {
                            return obj2.getSupplier_name().compareTo(obj1.getSupplier_name());                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    }

    public static void checkoutSort(ArrayAdapter<NewCheckout> adapter, List<NewCheckout> list, boolean flag, int sort_id) {
        if (!flag) {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj1.getDevice_name().compareTo(obj2.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj1.getDevice_SKU())-Integer.parseInt(obj2.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj1.getCount())-Integer.parseInt(obj2.getCount());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj1.getDate().compareTo(obj2.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 5:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj1.getPrice())-Integer.parseInt(obj2.getPrice());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 6:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj1.getBuyer_SKU().compareTo(obj2.getBuyer_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 7:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj1.getBuyer_name().compareTo(obj2.getBuyer_name());                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        } else {
            switch (sort_id) {
                case 0:
                case 1:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj2.getDevice_name().compareTo(obj1.getDevice_name());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj2.getDevice_SKU())-Integer.parseInt(obj1.getDevice_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj2.getCount())-Integer.parseInt(obj1.getCount());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj2.getDate().compareTo(obj1.getDate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 5:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return Integer.parseInt(obj2.getPrice())-Integer.parseInt(obj1.getPrice());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 6:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj2.getBuyer_SKU().compareTo(obj1.getBuyer_SKU());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                case 7:
                    Collections.sort(list, new Comparator<NewCheckout>() {
                        @Override
                        public int compare(NewCheckout obj1, NewCheckout obj2) {
                            return obj2.getBuyer_name().compareTo(obj1.getBuyer_name());                        }
                    });
                    adapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }
    }

}
