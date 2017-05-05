/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.controller.factory;

import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * Since we cannot bind a {@link Provider} to {@link Injector}, we use this interface to 'wrap' this specific provider
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-23
 */
interface ChildInjectorProvider extends Provider<Injector>
{
}
