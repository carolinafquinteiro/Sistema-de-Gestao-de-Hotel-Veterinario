package hva;

import hva.Hotel;

/**
 * Interface representing a visitor that can perform operations on a Hotel instance
 * Visits a specific hotel and performs an operation
 * 
 * @param hotel the Hotel instance to be visited
 */
public interface HotelVisitor {
    void visit(Hotel hotel);
}
