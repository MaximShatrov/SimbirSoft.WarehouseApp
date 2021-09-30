package com.simbirsoft.shatrov.WarehouseApp.service.item;

import com.simbirsoft.shatrov.WarehouseApp.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Item;
import com.simbirsoft.shatrov.WarehouseApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void createItem(Item item) throws NullEntityException {
        if (itemFieldsCheck(item)) {
            //debugMethod(item, "DEBUG");
            itemRepository.save(item);
            return;
        }
        throw new NullEntityException("Item has incorrect fields or NULL!");
    }

    @Override
    public Item readItem(Integer id) throws EntityNotFoundException {
        if (itemRepository.existsById(id)) {
            return itemRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Item with id:" + id + " not found.");
    }

    @Override
    public void updateItem(Item item) throws EntityNotFoundException, NullEntityException {
        if (!itemFieldsCheck(item)) {
            throw new NullEntityException("Item has incorrect fields or null!");
        }
        if (itemRepository.existsById(item.getId())) {
            itemRepository.save(item);
            return;
        }
        throw new EntityNotFoundException("Item with id:" + item.getId() + " not found.");
    }

    @Override
    public void deleteItem(Integer id) throws EntityNotFoundException {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("Item with id:" + id + " not found.");
    }

    @Override
    public List<Item> findAll() throws EntityNotFoundException {
        if (itemRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("DB is empty.");
        }
        return itemRepository.findAll();
    }

    private boolean itemFieldsCheck(Item item) {
        return item != null && !item.getName().isEmpty() && !item.getDescription().isEmpty() &&
                item.getAmount() != null && item.getPrice() != null;
    }

    private void debugMethod(Item item, String string) {
        System.err.println(string);
        System.out.println(item.getCategory().getId());
        System.out.println(item.getCategory().getName());
    }
}
