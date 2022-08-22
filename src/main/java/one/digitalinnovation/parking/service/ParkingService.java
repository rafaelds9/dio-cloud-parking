package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id){
        return parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    @org.springframework.transaction.annotation.Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setColor(parkingCreate.getColor());
        parkingCreate.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @org.springframework.transaction.annotation.Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
