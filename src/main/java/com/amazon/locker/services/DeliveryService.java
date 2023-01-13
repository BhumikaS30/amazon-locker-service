package com.amazon.locker.services;

import java.util.List;

import com.amazon.locker.exceptions.PackageSizeMappingException;
import com.amazon.locker.models.Item;
import com.amazon.locker.models.Locker;
import com.amazon.locker.models.LockerPackage;
import com.amazon.locker.models.LockerSize;
import com.amazon.locker.models.Order;
import com.amazon.locker.models.Pack;
import com.amazon.locker.utils.IdGenerator;

import static com.amazon.locker.models.LockerStatus.CLOSED;
import static com.amazon.locker.utils.Util.getLockerSizeForPack;
import static java.util.UUID.randomUUID;

public class DeliveryService {

    private final OrderService orderService = new OrderService();
    private final LockerService lockerService = new LockerService();
    private final NotificationService notificationService = new NotificationService();

    /**
     * Package is delivered in 4 steps -
     * <ui>
     *     <li>
     *         Get the order by order Id and then get the items
     *     </li>
     *     <li>
     *         Pack the items
     *     </li>
     *     <li>
     *         Get the locker by locker size and location (delivery address location)
     *     </li>
     *     <li>
     *         Create Locker package with the 6 digit code
     *     </li>
     *     <li>
     *         Notify the customer
     *     </li>
     * </ui>
     * @param orderId
     */
    public void deliver(String orderId) throws PackageSizeMappingException {
        Order order = orderService.getOrder(orderId);
        List<Item> items = order.getItems();
        Pack pack = new Pack(orderId, items);
        LockerSize lockerSizeForPack = getLockerSizeForPack(pack.getPackageSize());
        Locker locker = lockerService.getLocker(lockerSizeForPack, order.getDeliveryGeoLocation());
        LockerPackage lockerPackage = new LockerPackage();
        lockerPackage.setId(randomUUID().toString());
        lockerPackage.setLockerId(locker.getId());
        lockerPackage.setOrderId(orderId);
        lockerPackage.setCode(IdGenerator.generateId(6));
        locker.setLockerStatus(CLOSED);
        notificationService.notifyCustomerOrder(lockerPackage);

    }

}
