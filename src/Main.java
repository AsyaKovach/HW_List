import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Малахов Василий Кузьмич", "+129381832"));
        contactList.addToEnd(new Contact(151, "Кравченко Екатерина Александровна", "+234432334"));
        contactList.addToEnd(new Contact(332, "Заровный Александр Сергеевич", "+2234234423"));
        contactList.addToEnd(new Contact(432, "Маринов Даниел Ненков", "+2342344234"));
        contactList.addToEnd(new Contact(556, "Нагорный Никита Владимирович", "+6678877777"));

        for(Contact contact: contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for(Contact contact: contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    // Последовательеый доступ к элементам списка
    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        // Добавление элементов в конец списка
        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }
             // Метод разворота связного списка
        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}