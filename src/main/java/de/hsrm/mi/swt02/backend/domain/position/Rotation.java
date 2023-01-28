package de.hsrm.mi.swt02.backend.domain.position;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rotation {
    private double _x;
    private double _y;
    private double _z;
    private String _order;

    public Rotation (double _x, double _y, double _z, String _order) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
        this._order = _order;
    }

    public Rotation () {
    }

    @Override
    public String toString () {
        return "Rotation [_x=" + _x + ", _y=" + _y + ", _z=" + _z + ", _order=" + _order + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(_x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(_y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(_z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((_order == null) ? 0 : _order.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rotation other = (Rotation) obj;
        if (Double.doubleToLongBits(_x) != Double.doubleToLongBits(other._x))
            return false;
        if (Double.doubleToLongBits(_y) != Double.doubleToLongBits(other._y))
            return false;
        if (Double.doubleToLongBits(_z) != Double.doubleToLongBits(other._z))
            return false;
        if (_order == null) {
            if (other._order != null)
                return false;
        } else if (!_order.equals(other._order))
            return false;
        return true;
    }


}
